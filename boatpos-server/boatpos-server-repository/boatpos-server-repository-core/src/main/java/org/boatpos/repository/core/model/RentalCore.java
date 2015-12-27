package org.boatpos.repository.core.model;

import org.boatpos.model.PaymentMethod;
import org.boatpos.model.PromotionAfterEntity;
import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.model.RentalEntity;
import org.boatpos.repository.api.model.*;
import org.boatpos.repository.api.values.*;
import org.boatpos.repository.core.mapping.RentalMapping;
import org.boatpos.service.api.bean.RentalBean;

import javax.enterprise.inject.spi.CDI;
import java.util.Collections;
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
                      ArrivalTime arrivalTime,
                      PriceCalculated priceCalculated,
                      PricePaid pricePaid,
                      Finished finished,
                      Deleted deleted,
                      PaymentMethod paymentMethod,
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
        setArrivalTime(arrivalTime);
        setPriceCalculated(priceCalculated);
        setPricePaid(pricePaid);
        setFinished(finished);
        setDeleted(deleted);
        setPaymentMethod(paymentMethod);
        setPromotion(promotion);
        addCommitments(commitments);
    }

    public RentalCore(RentalEntity rentalEntity) {
        super(rentalEntity);
    }

    public RentalCore(RentalBean rentalBean) {
        this(CDI.current().select(RentalMapping.class).get().mapDto(rentalBean));
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
        return new Day(getEntity().getDate());
    }

    @Override
    public Rental setDay(Day day) {
        getEntity().setDate(SimpleValueObject.nullSafe(day));
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
    public PriceCalculated getPriceCalculated() {
        return new PriceCalculated(getEntity().getPriceCalculated());
    }

    @Override
    public Rental setPriceCalculated(PriceCalculated priceCalculated) {
        getEntity().setPriceCalculated(SimpleValueObject.nullSafe(priceCalculated));
        return this;
    }

    @Override
    public PricePaid getPricePaid() {
        return new PricePaid(getEntity().getPricePaid());
    }

    @Override
    public Rental setPricePaid(PricePaid price) {
        getEntity().setPricePaid(SimpleValueObject.nullSafe(price));
        return this;
    }

    @Override
    public Finished isFinished() {
        return new Finished(getEntity().isFinished());
    }

    @Override
    public Rental setFinished(Finished finished) {
        getEntity().setFinished(SimpleValueObject.nullSafe(finished));
        return this;
    }

    @Override
    public Deleted isDeleted() {
        return new Deleted(getEntity().isDeleted());
    }

    @Override
    public Rental setDeleted(Deleted deleted) {
        getEntity().setDeleted(SimpleValueObject.nullSafe(deleted));
        return this;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return getEntity().getPaymentMethod();
    }

    @Override
    public Rental setPaymentMethod(PaymentMethod paymentMethod) {
        getEntity().setPaymentMethod(paymentMethod);
        return this;
    }

    @Override
    public Promotion getPromotion() {
        if (getEntity().getPromotion() instanceof PromotionBeforeEntity) {
            return new PromotionBeforeCore((PromotionBeforeEntity) getEntity().getPromotion());
        } else {
            return new PromotionAfterCore((PromotionAfterEntity) getEntity().getPromotion());
        }
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
        return CDI.current().select(RentalMapping.class).get().mapEntity(getEntity());
    }
}