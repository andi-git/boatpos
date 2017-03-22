package org.boatpos.common.domain.core.model;

import org.boatpos.common.domain.core.mapping.Mapping;
import org.boatpos.common.util.qualifiers.Current;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Dependent
public class BarMapping extends Mapping<BarEntity, BarBean> {

    @Inject
    @Current
    private EntityManager entityManager;

    public static BarMapping fromCDI() {
        return CDI.current().select(BarMapping.class).get();
    }
}
