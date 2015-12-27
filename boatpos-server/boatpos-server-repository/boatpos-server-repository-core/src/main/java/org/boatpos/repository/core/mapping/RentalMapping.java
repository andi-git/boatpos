package org.boatpos.repository.core.mapping;

import org.boatpos.model.RentalEntity;
import org.boatpos.service.api.bean.RentalBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;

/**
 * Mapping between {@link RentalEntity} and {@link RentalBean}.
 */
@Dependent
public class RentalMapping extends Mapping<RentalEntity, RentalBean> {

    public static RentalMapping fromCDI() {
        return CDI.current().select(RentalMapping.class).get();
    }
}
