package org.boatpos.common.repository.core.model;

import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Version;

public class FooCore extends DomainModelCore<Foo, FooEntity, FooBean> implements Foo {

    public FooCore(DomainId id, Version version) {
        super(id, version);
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
}