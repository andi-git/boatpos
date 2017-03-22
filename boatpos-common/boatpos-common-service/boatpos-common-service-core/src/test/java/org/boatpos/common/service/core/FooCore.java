package org.boatpos.common.service.core;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.domain.api.values.Version;
import org.boatpos.common.domain.core.model.MasterDataCore;

public class FooCore extends MasterDataCore<Foo, FooEntity> implements Foo {

    public FooCore(DomainId id, Version version, Enabled enabled, Priority priority, KeyBinding keyBinding, PictureUrl pictureUrl, PictureUrlThumb pictureUrlThumb) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
    }

    public FooCore(FooEntity foo) {
        super(foo);
    }

    public FooCore(FooBean fooBean) {
        this(FooMapping.fromCDI().mapDto(fooBean));
    }

    @Override
    public FooBean asDto() {
        return FooMapping.fromCDI().mapEntity(getEntity());
    }

    public static Foo createDummy1() {
        return new FooCore(new DomainId(1L), new Version(1), Enabled.TRUE, new Priority(1), new KeyBinding('x'), new PictureUrl(""), new PictureUrlThumb(""));
    }

    public static Foo createDummy2() {
        return new FooCore(new DomainId(2L), new Version(1), Enabled.TRUE, new Priority(1), new KeyBinding('x'), new PictureUrl(""), new PictureUrlThumb(""));
    }
}