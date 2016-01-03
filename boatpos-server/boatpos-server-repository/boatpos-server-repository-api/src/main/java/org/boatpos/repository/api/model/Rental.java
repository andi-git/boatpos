package org.boatpos.repository.api.model;

import org.boatpos.model.PaymentMethod;
import org.boatpos.model.RentalEntity;
import org.boatpos.repository.api.values.*;
import org.boatpos.service.api.bean.RentalBean;

import java.util.Optional;
import java.util.Set;

/**
 * The domain model for a rental.
 */
public interface Rental extends DomainModel<Rental, RentalEntity, RentalBean> {

    /**
     * Undo the delete the current {@link DomainModel}.
     */
    void undoDelete();

    DayId getDayId();

    Rental setDayId(DayId dayId);

    Day getDay();

    Rental setDay(Day day);

    Boat getBoat();

    Rental setBoat(Boat boat);

    DepartureTime getDepartureTime();

    Rental setDepartureTime(DepartureTime departureTime);

    ArrivalTime getArrivalTime();

    Rental setArrivalTime(ArrivalTime arrivalTime);

    PriceCalculatedAfter getPriceCalculatedAfter();

    Rental setPriceCalculatedAfter(PriceCalculatedAfter priceCalculatedAfter);

    PricePaidAfter getPricePaidAfter();

    Rental setPricePaidAfter(PricePaidAfter pricePaidAfter);

    PricePaidBefore getPricePaidBefore();

    Rental setPricePaidBefore(PricePaidBefore pricePaidBefore);

    Finished isFinished();

    Rental setFinished(Finished finished);

    Deleted isDeleted();

    Rental setDeleted(Deleted deleted);

    PaymentMethod getPaymentMethod();

    Rental setPaymentMethod(PaymentMethod paymentMethod);

    Optional<Promotion> getPromotion();

    Rental setPromotion(Promotion promotion);

    Set<Commitment> getCommitments();

    Rental addCommitments(Set<Commitment> commitments);

    Rental addCommitment(Commitment commitment);

    Rental clearCommitments();

    PricePaidComplete getPricePaidComplete();
}
