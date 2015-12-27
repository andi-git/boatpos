package org.boatpos.repository.core.mapping;

import org.boatpos.model.BoatEntity;
import org.boatpos.repository.api.BoatPosDB;
import org.boatpos.service.api.bean.BoatBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Mapping between {@link BoatEntity} and {@link BoatBean}.
 */
@Dependent
public class BoatMapping extends Mapping<BoatEntity, BoatBean> {

    @Inject
    @BoatPosDB
    private EntityManager entityManager;

    public static BoatMapping fromCDI() {
        return CDI.current().select(BoatMapping.class).get();
    }
}
