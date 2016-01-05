package org.boatpos.repository.core.model;

import org.boatpos.model.BoatEntity;
import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.values.*;
import org.boatpos.repository.core.mapping.BoatMapping;
import org.boatpos.service.api.bean.BoatBean;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class BoatCore extends MasterDataCore<Boat, BoatEntity, BoatBean> implements Boat {

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
                    PictureUrlSmall pictureUrlSmall,
                    PictureUrlMedium pictureUrlMedium,
                    PictureUrlLarge pictureUrlLarge) {
        super(id, version, enabled, priority);
        checkNotNull(enabled, "'enabled' must not be null");
        checkNotNull(name, "'name' must not be null");
        checkNotNull(shortName, "'shortName' must not be null");
        checkNotNull(priceOneHour, "'priceOneHour' must not be null");
        checkNotNull(priceThrityMinutes, "'priceThirteenMinutes' must not be null");
        checkNotNull(priceFortyFiveMinutes, "'priceFortyFiveMinutes' must not be null");
        checkNotNull(count, "'count' must not be null");
        checkNotNull(pictureUrlSmall, "'pictureUrlSmall' must not be null");
        checkNotNull(pictureUrlMedium, "'pictureUrlMedium' must not be null");
        checkNotNull(pictureUrlLarge, "'pictureUrlLarge' must not be null");
        setName(name);
        setShortName(shortName);
        setPriceOneHour(priceOneHour);
        setPriceThirtyMinutes(priceThrityMinutes);
        setPriceFortyFiveMinutes(priceFortyFiveMinutes);
        setCount(count);
        setRentals(rentals);
        setPictureUrlSmall(pictureUrlSmall);
        setPictureUrlMedium(pictureUrlMedium);
        setPictureUrlLarge(pictureUrlLarge);
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
    public PictureUrlSmall getPictureUrlSmall() {
        return new PictureUrlSmall(getEntity().getPictureUrlSmall());
    }

    @Override
    public Boat setPictureUrlSmall(PictureUrlSmall pictureUrlSmall) {
        getEntity().setPictureUrlSmall(SimpleValueObject.nullSafe(pictureUrlSmall));
        return this;
    }

    @Override
    public PictureUrlMedium getPictureUrlMedium() {
        return new PictureUrlMedium(getEntity().getPictureUrlMedium());
    }

    @Override
    public Boat setPictureUrlMedium(PictureUrlMedium pictureUrlMedium) {
        getEntity().setPictureUrlMedium(SimpleValueObject.nullSafe(pictureUrlMedium));
        return this;
    }

    @Override
    public PictureUrlLarge getPictureUrlLarge() {
        return new PictureUrlLarge(getEntity().getPictureUrlLarge());
    }

    @Override
    public Boat setPictureUrlLarge(PictureUrlLarge pictureUrlLarge) {
        getEntity().setPictureUrlLarge(SimpleValueObject.nullSafe(pictureUrlLarge));
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