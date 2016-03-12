package org.boatpos.repository.core.model;

import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.values.Deleted;
import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.common.repository.api.values.Version;
import org.boatpos.model.PaymentMethod;
import org.boatpos.model.PromotionAfterEntity;
import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.model.RentalEntity;
import org.boatpos.repository.api.model.*;
import org.boatpos.repository.api.values.*;
import org.boatpos.repository.core.mapping.RentalMapping;
import org.boatpos.service.api.bean.RentalBean;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class RentalCore extends DomainModelCore<Rental, RentalEntity, RentalBean> implements Rental {

    public RentalCore(DomainId id,
                      Version version,
                      DayId dayId,
                      Day day,
                      Boat boat,
                      DepartureTime departureTime,
                      PriceCalculatedBefore priceCalculatedBefore,
                      PricePaidBefore pricePaidBefore,
                      ArrivalTime arrivalTime,
                      PriceCalculatedAfter priceCalculatedAfter,
                      PricePaidAfter pricePaidAfter,
                      Finished finished,
                      Deleted deleted,
                      PaymentMethod paymentMethodBefore,
                      PaymentMethod paymentMethodAfter,
                      Promotion promotion,
                      Set<Commitment> commitments) {
        super(id, version);
        checkNotNull(dayId, "'dayId' must not be null");
        checkNotNull(day, "'day' must not be null");
        checkNotNull(boat, "'boat' must not be null");
        checkNotNull(departureTime, "'departureTime' must not be null");
        setDayId(dayId);
        setDay(day);
        setBoat(boat);
        setDepartureTime(departureTime);
        setPriceCalculatedBefore(priceCalculatedBefore);
        setPricePaidBefore(pricePaidBefore);
        setArrivalTime(arrivalTime);
        setPriceCalculatedAfter(priceCalculatedAfter);
        setPricePaidAfter(pricePaidAfter);
        setFinished(finished);
        setDeleted(deleted);
        setPaymentMethodBefore(paymentMethodBefore);
        setPaymentMethodAfter(paymentMethodAfter);
        setPromotion(promotion);
        addCommitments(commitments);
    }

    public RentalCore(RentalEntity rentalEntity) {
        super(rentalEntity);
    }

    public RentalCore(RentalBean rentalBean) {
        this(RentalMapping.fromCDI().mapDto(rentalBean));
    }

    @Override
    public DayId getDayId() {
        return new DayId(getEntity().getDayId());
    }

    @Override
    public Rental setDayId(DayId dayId) {
        getEntity().setDayId(SimpleValueObject.nullSafe(dayId));
        return this;
    }

    @Override
    public Day getDay() {
        return new Day(getEntity().getDay());
    }

    @Override
    public Rental setDay(Day day) {
        getEntity().setDay(SimpleValueObject.nullSafe(day));
        return this;

    }

    @Override
    public Boat getBoat() {
        return new BoatCore(getEntity().getBoat());
    }

    @Override
    public Rental setBoat(Boat boat) {
        if (boat != null) getEntity().setBoat(boat.asEntity());
        return this;
    }

    @Override
    public DepartureTime getDepartureTime() {
        return new DepartureTime(getEntity().getDeparture());
    }

    @Override
    public Rental setDepartureTime(DepartureTime departureTime) {
        getEntity().setDeparture(SimpleValueObject.nullSafe(departureTime));
        return this;
    }

    @Override
    public ArrivalTime getArrivalTime() {
        return new ArrivalTime(getEntity().getArrival());
    }

    @Override
    public Rental setArrivalTime(ArrivalTime arrivalTime) {
        getEntity().setArrival(SimpleValueObject.nullSafe(arrivalTime));
        return this;
    }

    @Override
    public PriceCalculatedBefore getPriceCalculatedBefore() {
        return new PriceCalculatedBefore(getEntity().getPriceCalculatedBefore());
    }

    @Override
    public Rental setPriceCalculatedBefore(PriceCalculatedBefore priceCalculatedBefore) {
        getEntity().setPriceCalculatedBefore(SimpleValueObject.nullSafe(priceCalculatedBefore));
        return this;
    }

    @Override
    public PriceCalculatedAfter getPriceCalculatedAfter() {
        return new PriceCalculatedAfter(getEntity().getPriceCalculatedAfter());
    }

    @Override
    public Rental setPriceCalculatedAfter(PriceCalculatedAfter priceCalculatedAfter) {
        getEntity().setPriceCalculatedAfter(SimpleValueObject.nullSafe(priceCalculatedAfter));
        return this;
    }

    @Override
    public PricePaidAfter getPricePaidAfter() {
        return new PricePaidAfter(getEntity().getPricePaidAfter());
    }

    @Override
    public Rental setPricePaidAfter(PricePaidAfter pricePaidAfter) {
        getEntity().setPricePaidAfter(SimpleValueObject.nullSafe(pricePaidAfter));
        return this;
    }

    @Override
    public PricePaidBefore getPricePaidBefore() {
        return new PricePaidBefore(getEntity().getPricePaidBefore());
    }

    @Override
    public Rental setPricePaidBefore(PricePaidBefore pricePaidBefore) {
        getEntity().setPricePaidBefore(SimpleValueObject.nullSafe(pricePaidBefore));
        return this;
    }

    @Override
    public Finished isFinished() {
        return new Finished(getEntity().getFinished());
    }

    @Override
    public Rental setFinished(Finished finished) {
        getEntity().setFinished(SimpleValueObject.nullSafe(finished));
        return this;
    }

    @Override
    public Deleted isDeleted() {
        return new Deleted(getEntity().getDeleted());
    }

    @Override
    public Rental setDeleted(Deleted deleted) {
        getEntity().setDeleted(SimpleValueObject.nullSafe(deleted));
        return this;
    }

    @Override
    public PaymentMethod getPaymentMethodBefore() {
        return getEntity().getPaymentMethodBefore();
    }

    @Override
    public Rental setPaymentMethodBefore(PaymentMethod paymentMethodBefore) {
        getEntity().setPaymentMethodBefore(paymentMethodBefore);
        return this;
    }

    @Override
    public PaymentMethod getPaymentMethodAfter() {
        return getEntity().getPaymentMethodBefore();
    }

    @Override
    public Rental setPaymentMethodAfter(PaymentMethod paymentMethodAfter) {
        getEntity().setPaymentMethodAfter(paymentMethodAfter);
        return this;
    }

    @Override
    public Optional<Promotion> getPromotion() {
        Optional<Promotion> promotion = Optional.empty();
        if (getEntity().getPromotion() instanceof PromotionBeforeEntity) {
            promotion = Optional.of(new PromotionBeforeCore((PromotionBeforeEntity) getEntity().getPromotion()));
        } else if (getEntity().getPromotion() instanceof PromotionAfterEntity) {
            promotion = Optional.of(new PromotionAfterCore((PromotionAfterEntity) getEntity().getPromotion()));
        }
        return promotion;
    }

    @Override
    public Rental setPromotion(Promotion promotion) {
        if (promotion != null) getEntity().setPromotion(promotion.asEntity());
        return this;
    }

    @Override
    public Set<Commitment> getCommitments() {
        return Collections.unmodifiableSet(getEntity().getCommitments().stream().map(CommitmentCore::new).collect(Collectors.toSet()));
    }

    @Override
    public Rental addCommitments(Set<Commitment> commitments) {
        if (commitments != null) {
            getEntity().setCommitments(commitments.stream().map(DomainModel::asEntity).collect(Collectors.toSet()));
        }
        return this;
    }

    @Override
    public Rental addCommitment(Commitment commitment) {
        if (commitment != null) {
            getEntity().getCommitments().add(commitment.asEntity());
        }
        return this;
    }

    @Override
    public Rental clearCommitments() {
        getEntity().getCommitments().clear();
        return this;
    }

    @Override
    public PricePaidComplete getPricePaidComplete() {
        return new PricePaidComplete(getPricePaidBefore(), getPricePaidAfter());
    }

    @Override
    public void delete() {
        setDeleted(Deleted.TRUE);
        persist();
    }

    @Override
    public void undoDelete() {
        setDeleted(Deleted.FALSE);
        persist();
    }

    @Override
    public RentalBean asDto() {
        return RentalMapping.fromCDI().mapEntity(getEntity());
    }
}