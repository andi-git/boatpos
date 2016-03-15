package org.boatpos.common.service.core;

import org.boatpos.common.repository.core.mapping.Mapping;
import org.boatpos.common.util.qualifiers.Current;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Dependent
public class FooMapping extends Mapping<FooEntity, FooBean> {

    public static FooMapping fromCDI() {
        return CDI.current().select(FooMapping.class).get();
    }
}
