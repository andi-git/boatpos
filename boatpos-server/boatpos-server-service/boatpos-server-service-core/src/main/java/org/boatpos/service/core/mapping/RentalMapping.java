package org.boatpos.service.core.mapping;

import org.boatpos.model.Rental;
import org.boatpos.service.api.bean.RentalBean;

import javax.enterprise.context.Dependent;

/**
 * Mapping between {@link Rental} and {@link RentalBean}.
 */
@Dependent
public class RentalMapping extends Mapping<Rental, RentalBean> {

    @Override
    protected Class<RentalBean> getMappedDtoClass() {
        return RentalBean.class;
    }

    @Override
    protected Class<Rental> getMappedEntityClass() {
        return Rental.class;
    }
}
