package org.boatpos.domain.api.model;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.domain.api.model.DomainModel;
import org.boatpos.common.domain.api.model.DomainModelWithDto;
import org.boatpos.common.domain.api.values.Deleted;
import org.boatpos.domain.api.values.ArrivalTime;
import org.boatpos.domain.api.values.Day;
import org.boatpos.domain.api.values.DayId;
import org.boatpos.domain.api.values.DepartureTime;
import org.boatpos.domain.api.values.Finished;
import org.boatpos.domain.api.values.PriceCalculatedAfter;
import org.boatpos.domain.api.values.PriceCalculatedBefore;
import org.boatpos.domain.api.values.PricePaidAfter;
import org.boatpos.domain.api.values.PricePaidBefore;
import org.boatpos.domain.api.values.PricePaidComplete;
import org.boatpos.domain.api.values.ReceiptId;
import org.boatpos.model.RentalEntity;
import org.boatpos.service.api.bean.RentalBean;

import java.util.Optional;
import java.util.Set;

/**
 * The domain model for a rental.
 */
public interface Rental extends DomainModelWithDto<Rental, RentalEntity, RentalBean> {

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

    PriceCalculatedBefore getPriceCalculatedBefore();

    Rental setPriceCalculatedBefore(PriceCalculatedBefore priceCalculatedBefore);

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

    PaymentMethod getPaymentMethodBefore();

    Rental setPaymentMethodBefore(PaymentMethod paymentMethodBefore);

    PaymentMethod getPaymentMethodAfter();

    Rental setPaymentMethodAfter(PaymentMethod paymentMethodAfter);

    Optional<Promotion> getPromotion();

    Rental setPromotion(Promotion promotion);

    Rental removePromotion();

    Set<Commitment> getCommitments();

    Rental addCommitments(Set<Commitment> commitments);

    Rental addCommitment(Commitment commitment);

    Rental clearCommitments();

    PricePaidComplete getPricePaidComplete();

    ReceiptId getReceiptId();

    Rental setReceiptId(ReceiptId receiptId);
}
