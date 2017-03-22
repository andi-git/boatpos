package org.boatpos.common.domain.core.model;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Version;

public class FooCore extends DomainModelCore<Foo, FooEntity> implements Foo {

    public FooCore(DomainId id, Version version) {
        super(id, version);
    }

    public FooCore(FooEntity foo) {
        super(foo);
    }

    public FooCore(FooBean fooBean) {
        this(FooMapping.fromCDI().mapDto(fooBean));
    }
}