package org.boatpos.common.domain.core.model;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Version;

public class BarCore extends DomainModelCore<Bar, BarEntity> implements Bar {

    public BarCore(DomainId id, Version version) {
        super(id, version);
    }

    public BarCore(BarEntity bar) {
        super(bar);
    }

    public BarCore(BarBean barBean) {
        this(BarMapping.fromCDI().mapDto(barBean));
    }

    @Override
    public BarBean asDto() {
        return null;
    }
}