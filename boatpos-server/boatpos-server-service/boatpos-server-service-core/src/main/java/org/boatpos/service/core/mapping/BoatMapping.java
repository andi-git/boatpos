package org.boatpos.service.core.mapping;

import org.boatpos.model.Boat;
import org.boatpos.service.api.bean.BoatBean;

import javax.enterprise.context.Dependent;

/**
 * Mapping between {@link Boat} and {@link BoatBean}.
 */
@Dependent
public class BoatMapping extends Mapping<Boat, BoatBean> {

    @Override
    protected Class<BoatBean> getMappedDtoClass() {
        return BoatBean.class;
    }

    @Override
    protected Class<Boat> getMappedEntityClass() {
        return Boat.class;
    }
}
