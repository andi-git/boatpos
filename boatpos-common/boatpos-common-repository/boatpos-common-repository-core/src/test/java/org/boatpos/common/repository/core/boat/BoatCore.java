package org.boatpos.common.repository.core.boat;

import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.repository.core.model.MasterDataCore;

import static com.google.common.base.Preconditions.checkNotNull;

public class BoatCore extends MasterDataCore<Boat, BoatEntity, BoatBean> implements Boat {

    public BoatCore(DomainId id,
                    Version version,
                    Enabled enabled,
                    Priority priority,
                    Name name,
                    Price price,
                    KeyBinding keyBinding,
                    PictureUrl pictureUrl,
                    PictureUrlThumb pictureUrlThumb) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(enabled, "'enabled' must not be null");
        checkNotNull(name, "'name' must not be null");
        checkNotNull(price, "'priceOneHour' must not be null");
        setName(name);
        setPrice(price);
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
    public Price getPrice() {
        return new Price(getEntity().getPrice());
    }

    @Override
    public Boat setPrice(Price price) {
        getEntity().setPrice(SimpleValueObject.nullSafe(price));
        return this;
    }

    @Override
    public BoatBean asDto() {
        return BoatMapping.fromCDI().mapEntity(getEntity());
    }

    public static BoatCore createDummyWithoutId() {
        return new BoatCore(null, null, Enabled.TRUE, new Priority(5), new Name("aName"), new Price("9.50"), new KeyBinding('x'), new PictureUrl("pic"), new PictureUrlThumb("thumb"));
    }

    public static BoatCore createDummyWithId1() {
        return new BoatCore(new DomainId(99L), new Version(0), Enabled.TRUE, new Priority(5), new Name("aName"), new Price("9.50"), new KeyBinding('x'), new PictureUrl("pic"), new PictureUrlThumb("thumb"));
    }

    public static BoatCore createDummyWithId2() {
        return new BoatCore(new DomainId(98L), new Version(0), Enabled.TRUE, new Priority(5), new Name("aName"), new Price("9.50"), new KeyBinding('x'), new PictureUrl("pic"), new PictureUrlThumb("thumb"));
    }

    public static BoatCore createDummyWithId3() {
        return new BoatCore(new DomainId(99L), new Version(0), Enabled.TRUE, new Priority(5), new Name("aName"), new Price("9.50"), new KeyBinding('x'), new PictureUrl("pic"), new PictureUrlThumb("thumb"));
    }
}