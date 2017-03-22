package org.boatpos.common.domain.core.model;

import org.boatpos.common.domain.core.mapping.Mapping;
import org.boatpos.common.util.qualifiers.Current;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Dependent
public class FooMapping extends Mapping<FooEntity, FooBean> {

    @Inject
    @Current
    private EntityManager entityManager;

    public static FooMapping fromCDI() {
        return CDI.current().select(FooMapping.class).get();
    }
}
