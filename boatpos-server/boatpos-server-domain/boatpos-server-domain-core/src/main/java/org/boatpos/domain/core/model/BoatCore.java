package org.boatpos.domain.core.model;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.common.domain.api.values.Version;
import org.boatpos.common.domain.core.model.MasterDataCore;
import org.boatpos.domain.api.values.Count;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.api.values.PriceFortyFiveMinutes;
import org.boatpos.domain.api.values.PriceOneHour;
import org.boatpos.domain.api.values.PriceThirtyMinutes;
import org.boatpos.domain.api.values.ShortName;
import org.boatpos.model.BoatEntity;
import org.boatpos.domain.api.model.Boat;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.core.mapping.BoatMapping;
import org.boatpos.service.api.bean.BoatBean;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class BoatCore extends MasterDataCore<Boat, BoatEntity> implements Boat {

    public BoatCore(DomainId id,
                    Version version,
                    Enabled enabled,
                    Priority priority,
                    Name name,
                    ShortName shortName,
                    PriceOneHour priceOneHour,
                    PriceThirtyMinutes priceThrityMinutes,
                    PriceFortyFiveMinutes priceFortyFiveMinutes,
                    Count count,
                    Set<Rental> rentals,
                    KeyBinding keyBinding,
                    PictureUrl pictureUrl,
                    PictureUrlThumb pictureUrlThumb) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(enabled, "'enabled' must not be null");
        checkNotNull(name, "'name' must not be null");
        checkNotNull(shortName, "'shortName' must not be null");
        checkNotNull(priceOneHour, "'priceOneHour' must not be null");
        checkNotNull(priceThrityMinutes, "'priceThirteenMinutes' must not be null");
        checkNotNull(priceFortyFiveMinutes, "'priceFortyFiveMinutes' must not be null");
        checkNotNull(count, "'count' must not be null");
        setName(name);
        setShortName(shortName);
        setPriceOneHour(priceOneHour);
        setPriceThirtyMinutes(priceThrityMinutes);
        setPriceFortyFiveMinutes(priceFortyFiveMinutes);
        setCount(count);
        setRentals(rentals);
        setPictureUrlThumb(pictureUrlThumb);
        setPictureUrl(pictureUrl);
        setKeyBinding(keyBinding);
    }

    public BoatCore(BoatEntity boat) {
        super(boat);
    }

    public BoatCore(BoatBean boatBean) {
        this(BoatMapping.fromCDI().mapDto(boatBean));
    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    @Override
    public Boat setName(Name name) {
        getEntity().setName(SimpleValueObject.nullSafe(name));
        return this;
    }

    @Override
    public ShortName getShortName() {
        return new ShortName(getEntity().getShortName());
    }

    @Override
    public Boat setShortName(ShortName shortName) {
        getEntity().setShortName(SimpleValueObject.nullSafe(shortName));
        return this;
    }

    @Override
    public PriceOneHour getPriceOneHour() {
        return new PriceOneHour(getEntity().getPriceOneHour());
    }

    @Override
    public Boat setPriceOneHour(PriceOneHour priceOneHour) {
        getEntity().setPriceOneHour(SimpleValueObject.nullSafe(priceOneHour));
        return this;
    }

    @Override
    public PriceThirtyMinutes getPriceThirtyMinutes() {
        return new PriceThirtyMinutes(getEntity().getPriceThirtyMinutes());
    }

    @Override
    public Boat setPriceThirtyMinutes(PriceThirtyMinutes priceThirtyMinutes) {
        getEntity().setPriceThirtyMinutes(SimpleValueObject.nullSafe(priceThirtyMinutes));
        return this;
    }

    @Override
    public PriceFortyFiveMinutes getPriceFortyFiveMinutes() {
        return new PriceFortyFiveMinutes(getEntity().getPriceFortyFiveMinutes());
    }

    @Override
    public Boat setPriceFortyFiveMinutes(PriceFortyFiveMinutes priceFortyFiveMinutes) {
        getEntity().setPriceFortyFiveMinutes(SimpleValueObject.nullSafe(priceFortyFiveMinutes));
        return this;
    }

    @Override
    public Count getCount() {
        return new Count(getEntity().getCount());
    }

    @Override
    public Boat setCount(Count count) {
        getEntity().setCount(SimpleValueObject.nullSafe(count));
        return this;
    }

    @Override
    public Set<Rental> getRentals() {
        return RentalConverter.getViaCDI().convert(getEntity().getRentals());
    }

    @Override
    public Boat setRentals(Set<Rental> rentals) {
        RentalConverter.getViaCDI().setRentals(getEntity(), rentals);
        return this;
    }

    @Override
    public Boat addRental(Rental rental) {
        RentalConverter.getViaCDI().addRental(getEntity(), rental);
        return this;
    }

    @Override
    public BoatBean asDto() {
        return BoatMapping.fromCDI().mapEntity(getEntity());
    }
}