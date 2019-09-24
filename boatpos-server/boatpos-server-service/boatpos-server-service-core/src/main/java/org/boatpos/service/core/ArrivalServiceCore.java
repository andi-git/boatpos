package org.boatpos.service.core;

import com.google.common.collect.Lists;
import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.util.qualifiers.Current;
import org.boatpos.domain.api.model.PromotionAfter;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.api.repository.PromotionAfterRepository;
import org.boatpos.domain.api.repository.RentalRepository;
import org.boatpos.domain.api.values.*;
import org.boatpos.service.api.ArrivalService;
import org.boatpos.service.api.bean.*;
import org.boatpos.service.core.util.DayIdLocker;
import org.boatpos.service.core.util.RegkasService;
import org.boatpos.service.core.util.RentalBeanEnrichment;
import org.boatpos.service.core.util.RentalLoader;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.SaleBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@RequestScoped
public class ArrivalServiceCore implements ArrivalService {

    @Inject
    private RentalRepository rentalRepository;

    @Inject
    private PriceCalculator priceCalculator;

    @Inject
    @Current
    private Day day;

    @Inject
    private RentalLoader rentalLoader;

    @Inject
    private PromotionAfterRepository promotionAfterRepository;

    @Inject
    @Current
    private ArrivalTime arrivalTime;

    @Inject
    private RentalBeanEnrichment rentalBeanEnrichment;

    @Inject
    private RegkasService regkasService;

    @Inject
    private DayIdLocker dayIdLocker;

    @Override
    public RentalBean arrive(ArrivalBean arrivalBean) {
        DayId dayId = new DayId(arrivalBean.getDayNumber());
        rentalLoader.checkIfRentalIsActive(dayId);
        Rental rental = rentalRepository.arrive(
                day,
                dayId,
                arrivalTime);
        PriceCalculatedAfter priceCalculatedAfter = priceCalculator.calculate(rental);
        return rentalBeanEnrichment.asDto(rental.setPriceCalculatedAfter(priceCalculatedAfter).persist());
    }

    @Override
    public RentalBean addPromotion(AddPromotionBean addPromotionBean) {
        checkNotNull(addPromotionBean, "'addPromotionBean' must not be null");
        DayId dayId = new DayId(addPromotionBean.getDayNumber());
        Optional<Rental> rentalOptional = rentalRepository.loadBy(day, dayId);
        if (rentalOptional.isPresent()) {
            Optional<PromotionAfter> promotionAfterOptional = promotionAfterRepository.loadBy(new DomainId(addPromotionBean.getPromotionId()));
            if (promotionAfterOptional.isPresent()) {
                Rental rental = rentalOptional.get();
                rental.setPromotion(promotionAfterOptional.get());
                rental.setPriceCalculatedAfter(priceCalculator.calculate(rental));
                return rentalBeanEnrichment.asDto(rental.persist());
            } else {
                throw new RuntimeException("unable to load promotion with id " + addPromotionBean.getPromotionId());
            }
        } else {
            throw new RuntimeException("unable to add a promotion because rental with dayId " + addPromotionBean.getDayNumber() + " doesn't exist");
        }
    }

    @Override
    public RentalBean removePromotionsAfter(RemovePromotionsAfterBean removePromotionsAfterBean) {
        checkNotNull(removePromotionsAfterBean);
        DayId dayId = new DayId(removePromotionsAfterBean.getDayNumber());
        Optional<Rental> rentalOptional = rentalRepository.loadBy(day, dayId);
        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            rental.removePromotion();
            rental.setPriceCalculatedAfter(priceCalculator.calculate(rental));
            return rentalBeanEnrichment.asDto(rental.persist());
        } else {
            throw new RuntimeException("unable to add a promotion because rental with dayId " + dayId.get() + " doesn't exist");
        }
    }

    @Override
    public BillBean pay(PaymentBean paymentBean) {
        // check rental
        DayId dayId = new DayId(paymentBean.getDayNumber());
        if (dayIdLocker.isLocked(dayId)) {
            throw new IllegalStateException("Payment not possible - day-id " + dayId.toString() + " is locked");
        }
        try {
            dayIdLocker.lock(dayId);
            rentalLoader.checkIfRentalIsActive(dayId);
            Rental rental = rentalLoader.loadOnCurrentDayBy(dayId);
            if (rental.isFinished() != null && rental.isFinished().get()) {
                throw new IllegalStateException("Payment not possible - rental is already finished!");
            }
            // update rental
            rental.setPricePaidAfter(new PricePaidAfter(paymentBean.getValue()))
                    .setFinished(Finished.TRUE)
                    .setPaymentMethodAfter(PaymentMethod.get(paymentBean.getPaymentMethod()))
                    .persist();
            // create bill via regkas
            try {
                BillBean billBean = regkasService.sale(paymentBean);
                rental.setReceiptId(new ReceiptId(billBean.getReceiptIdentifier())).persist();
                return billBean;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } finally {
            dayIdLocker.release(dayId);
        }
    }

    @Override
    public BillBean receipt(String receiptType) {
        try {
            return regkasService.receipt(new SaleBean("CASH", receiptType, Lists.newArrayList()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean isSignatureDeviceAvailable() {
        return regkasService.isSignatureDeviceAvailable();
    }

    @Override
    public Boolean checkIfStartbelegMustBePrinted() {
        return regkasService.checkIfStartreceiptMustBePrinted();
    }

    @Override
    public Boolean checkIfSchlussbelegCanBePrinted() {
        return regkasService.checkIfSchlussreceiptCanBePrinted();
    }
}
