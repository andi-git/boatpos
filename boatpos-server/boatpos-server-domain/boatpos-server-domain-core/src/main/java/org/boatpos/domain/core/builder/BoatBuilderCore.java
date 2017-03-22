package org.boatpos.domain.core.builder;

import org.boatpos.common.domain.core.builder.MasterDataBuilderCoreWithDto;
import org.boatpos.domain.api.values.Count;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.api.values.PriceFortyFiveMinutes;
import org.boatpos.domain.api.values.PriceOneHour;
import org.boatpos.domain.api.values.PriceThirtyMinutes;
import org.boatpos.domain.api.values.ShortName;
import org.boatpos.model.BoatEntity;
import org.boatpos.domain.api.builder.BoatBuilder;
import org.boatpos.domain.api.model.Boat;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.core.model.BoatCore;
import org.boatpos.service.api.bean.BoatBean;

import javax.enterprise.context.Dependent;
import java.util.HashSet;
import java.util.Set;

@Dependent
public class BoatBuilderCore
        extends MasterDataBuilderCoreWithDto<BoatBuilder, Boat, BoatCore, BoatEntity, BoatBean>
        implements BoatBuilder {

    private Name name;
    private ShortName shortName;
    private PriceOneHour priceOneHour;
    private PriceThirtyMinutes priceThirtyMinutes;
    private PriceFortyFiveMinutes priceFortyFiveMinutes;
    private Count count;
    private Set<Rental> rentals = new HashSet<>();

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
