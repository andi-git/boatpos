package org.boatpos.service.core;

import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.model.Commitment;
import org.boatpos.repository.api.model.PromotionBefore;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.repository.BoatRepository;
import org.boatpos.repository.api.repository.CommitmentRepository;
import org.boatpos.repository.api.repository.PromotionBeforeRepository;
import org.boatpos.repository.api.repository.RentalRepository;
import org.boatpos.repository.api.values.*;
import org.boatpos.service.api.DepartureService;
import org.boatpos.service.api.bean.DepartureBean;
import org.boatpos.service.api.bean.PaymentBean;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.service.core.util.RentalBeanEnrichment;
import org.boatpos.service.core.util.RentalLoader;
import org.boatpos.util.qualifiers.Current;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequestScoped
public class DepartureServiceCore implements DepartureService {

    @Inject
    private BoatRepository boatRepository;

    @Inject
    private CommitmentRepository commitmentRepository;

    @Inject
    private PromotionBeforeRepository promotionBeforeRepository;

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
    @Current
    private DepartureTime departureTime;

    @Inject
    private RentalBeanEnrichment rentalBeanEnrichment;

    @Override
    public RentalBean depart(DepartureBean departureBean) {
        Boat boat = getBoat(departureBean);
        Optional<PromotionBefore> promotionBefore = getPromotionBefore(departureBean);
        Optional<PriceCalculatedBefore> priceCalculatedBefore = Optional.empty();
        if (promotionBefore.isPresent()) {
            priceCalculatedBefore = Optional.of(priceCalculator.calculate(boat.getPriceOneHour(), promotionBefore.get()));
        }
        return rentalBeanEnrichment.asDto(rentalRepository
                .depart(
                        day,
                        departureTime,
                        boat,
                        getCommitments(departureBean),
                        promotionBefore,
                        priceCalculatedBefore));
    }

    @Override
    public RentalBean pay(PaymentBean paymentBean) {
        Rental rental = rentalLoader.loadOnCurrentDayBy(new DayId(paymentBean.getDayNumber()));
        if (!rental.getPromotion().isPresent() || !(rental.getPromotion().get() instanceof PromotionBefore)) {
            throw new RuntimeException("rental " + paymentBean.getDayNumber() + ": unable to pay for a promotion when no promotion-before is set");
        }
        return rentalBeanEnrichment.asDto(rental.setPricePaidBefore(new PricePaidBefore(paymentBean.getValue()))
                .persist());
    }

    private Optional<PromotionBefore> getPromotionBefore(DepartureBean departureBean) {
        return promotionBeforeRepository.loadBy(new DomainId(departureBean.getPromotionId()));
    }

    private Set<Commitment> getCommitments(DepartureBean departureBean) {
        Set<Commitment> commitments = new HashSet<>();
        if (!departureBean.getCommitmentIds().isEmpty()) {
            departureBean.getCommitmentIds().forEach(
                    cb -> commitments.add(getOrThrowException(commitmentRepository.loadBy(new DomainId(cb)), createExceptionMessage(Commitment.class, new DomainId(cb))))
            );
        }
        return commitments;
    }

    private Boat getBoat(DepartureBean departureBean) {
        DomainId boatId = new DomainId(departureBean.getBoatId());
        return getOrThrowException(boatRepository.loadBy(boatId), createExceptionMessage(Boat.class, boatId));
    }

    private <T> T getOrThrowException(Optional<T> optional, String message) {
        return optional.orElseGet(() -> {
            throw new RuntimeException(message);
        });
    }

    private String createExceptionMessage(Class<?> type, SimpleValueObject simpleValueObject) {
        return "no " + type.getName() + " available with fot " + simpleValueObject;
    }
}
