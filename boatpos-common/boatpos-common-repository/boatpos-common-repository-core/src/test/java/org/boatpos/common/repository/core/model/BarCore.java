package org.boatpos.common.repository.core.model;

import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Version;

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