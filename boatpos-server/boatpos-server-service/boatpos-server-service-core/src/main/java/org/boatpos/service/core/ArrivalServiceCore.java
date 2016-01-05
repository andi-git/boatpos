package org.boatpos.service.core;

import org.boatpos.repository.api.model.PromotionAfter;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.repository.PromotionAfterRepository;
import org.boatpos.repository.api.repository.RentalRepository;
import org.boatpos.repository.api.values.*;
import org.boatpos.service.api.ArrivalService;
import org.boatpos.service.api.bean.AddPromotionBean;
import org.boatpos.service.api.bean.ArrivalBean;
import org.boatpos.service.api.bean.PaymentBean;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.service.core.util.RentalLoader;
import org.boatpos.util.qualifiers.Current;

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

    @Override
    public RentalBean arrive(ArrivalBean arrivalBean) {
        Rental rental = rentalRepository.arrive(
                day,
                new DayId(arrivalBean.getDayNumber()),
                arrivalTime);
        PriceCalculatedAfter priceCalculatedAfter = priceCalculator.calculate(rental);
        return rental.setPriceCalculatedAfter(priceCalculatedAfter).persist().asDto();
    }

    @Override
    public RentalBean addPromotion(AddPromotionBean addPromotionBean) {
        checkNotNull(addPromotionBean, "'addPromotionBean' must not be null");
        Optional<Rental> rentalOptional = rentalRepository.loadBy(day, new DayId(addPromotionBean.getDayNumber()));
        if (rentalOptional.isPresent()) {
            Optional<PromotionAfter> promotionAfterOptional = promotionAfterRepository.loadBy(new DomainId(addPromotionBean.getPromotionId()));
            if (promotionAfterOptional.isPresent()) {
                Rental rental = rentalOptional.get();
                rental.setPromotion(promotionAfterOptional.get());
                rental.setPriceCalculatedAfter(priceCalculator.calculate(rental));
                return rental.persist().asDto();
            } else {
                throw new RuntimeException("unable to load promotion with id " + addPromotionBean.getPromotionId());
            }
        } else {
            throw new RuntimeException("unable to add a promotion because rental with dayId " + addPromotionBean.getDayNumber() + " doesn't exist");
        }
    }

    @Override
    public RentalBean pay(PaymentBean paymentBean) {
        return rentalLoader.loadOnCurrentDayBy(new DayId(paymentBean.getDayNumber()))
                .setPricePaidAfter(new PricePaidAfter(paymentBean.getValue()))
                .persist()
                .asDto();
    }
}
