package org.boatpos.repository.core.builder;

import org.boatpos.common.repository.core.builder.DomainModelBuilderCore;
import org.boatpos.model.PaymentMethod;
import org.boatpos.model.RentalEntity;
import org.boatpos.repository.api.builder.RentalBuilder;
import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.model.Commitment;
import org.boatpos.repository.api.model.PromotionBefore;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.values.Day;
import org.boatpos.repository.api.values.DayId;
import org.boatpos.repository.api.values.DepartureTime;
import org.boatpos.repository.api.values.PriceCalculatedBefore;
import org.boatpos.repository.core.model.RentalCore;
import org.boatpos.service.api.bean.RentalBean;

import javax.enterprise.context.Dependent;
import java.util.HashSet;
import java.util.Set;

@Dependent
public class RentalBuilderCore extends DomainModelBuilderCore<RentalBuilder, Rental, RentalCore, RentalEntity, RentalBean> implements RentalBuilder {

    protected DayId dayId;
    protected Day day;
    protected Boat boat;
    protected DepartureTime departureTime;
    protected PromotionBefore promotionBefore;
    protected PriceCalculatedBefore priceCalculatedBefore;
    protected Set<Commitment> commitments = new HashSet<>();
    protected PaymentMethod paymentMethodBefore;

    @Override
    public RentalBuilder add(DayId dayId) {
        this.dayId = dayId;
        return this;
    }

    @Override
    public RentalBuilder add(Day day) {
        this.day = day;
        return this;
    }

    @Override
    public RentalBuilder add(Boat boat) {
        this.boat = boat;
        return this;
    }

    @Override
    public RentalBuilder add(DepartureTime departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    @Override
    public RentalBuilder add(PromotionBefore promotionBefore) {
        this.promotionBefore = promotionBefore;
        return this;
    }

    @Override
    public RentalBuilder add(Set<Commitment> commitments) {
        this.commitments.addAll(commitments);
        return this;
    }

    @Override
    public RentalBuilder add(Commitment commitment) {
        this.commitments.add(commitment);
        return this;
    }

    @Override
    public RentalBuilder add(PriceCalculatedBefore priceCalculatedBefore) {
        this.priceCalculatedBefore = priceCalculatedBefore;
        return this;
    }

    @Override
    public RentalBuilder add(PaymentMethod paymentMethodBefore) {
        this.paymentMethodBefore = paymentMethodBefore;
        return this;
    }

    @Override
    public Rental build() {
        return new RentalCore(id, version, dayId, day, boat, departureTime, priceCalculatedBefore, null, null, null, null, null, null, paymentMethodBefore, null, promotionBefore, commitments);
    }
}
