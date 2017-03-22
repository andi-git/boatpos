package org.boatpos.common.service.core;

import org.boatpos.common.domain.core.mapping.Mapping;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;

@Dependent
public class FooMapping extends Mapping<FooEntity, FooBean> {

    public static FooMapping fromCDI() {
        return CDI.current().select(FooMapping.class).get();
    }
}
