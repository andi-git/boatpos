package org.boatpos.repository.core.builder;

import org.boatpos.common.repository.core.builder.MasterDataBuilderCore;
import org.boatpos.model.BoatEntity;
import org.boatpos.repository.api.builder.BoatBuilder;
import org.boatpos.repository.api.builder.RentalBuilder;
import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.values.*;
import org.boatpos.repository.core.model.BoatCore;
import org.boatpos.service.api.bean.BoatBean;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@Dependent
public class BoatBuilderCore extends MasterDataBuilderCore<BoatBuilder, Boat, BoatCore, BoatEntity, BoatBean> implements BoatBuilder {

    private Name name;
    private ShortName shortName;
    private PriceOneHour priceOneHour;
    private PriceThirtyMinutes priceThirtyMinutes;
    private PriceFortyFiveMinutes priceFortyFiveMinutes;
    private Count count;
    private Set<Rental> rentals = new HashSet<>();

    @Inject
    private RentalBuilder rentalBuilder;

    @Override
    public BoatBuilder add(Name name) {
        this.name = name;
        return this;
    }

    @Override
    public BoatBuilder add(ShortName shortName) {
        this.shortName = shortName;
        return this;
    }

    @Override
    public BoatBuilder add(PriceOneHour priceOneHour) {
        this.priceOneHour = priceOneHour;
        return this;
    }

    @Override
    public BoatBuilder add(PriceThirtyMinutes priceThirtyMinutes) {
        this.priceThirtyMinutes = priceThirtyMinutes;
        return this;
    }

    @Override
    public BoatBuilder add(PriceFortyFiveMinutes priceFortyFiveMinutes) {
        this.priceFortyFiveMinutes = priceFortyFiveMinutes;
        return this;
    }

    @Override
    public BoatBuilder add(Count count) {
        this.count = count;
        return this;
    }

    @Override
    public BoatBuilder add(Set<Rental> rentals) {
        this.rentals.addAll(rentals);
        return this;
    }

    @Override
    public BoatBuilder add(Rental rental) {
        rentals.add(rental);
        return this;
    }

    @Override
    public Boat build() {
        return new BoatCore(id, version, enabled, priority, name, shortName, priceOneHour, priceThirtyMinutes, priceFortyFiveMinutes, count, rentals, keyBinding, pictureUrl, pictureUrlThumb);
    }
}
