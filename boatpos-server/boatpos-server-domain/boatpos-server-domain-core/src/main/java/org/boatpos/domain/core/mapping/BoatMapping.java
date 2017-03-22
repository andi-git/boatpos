package org.boatpos.domain.core.mapping;

import org.boatpos.common.domain.core.mapping.Mapping;
import org.boatpos.common.util.qualifiers.Current;
import org.boatpos.model.BoatEntity;
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
    @Current
    private EntityManager entityManager;

    public static BoatMapping fromCDI() {
        return CDI.current().select(BoatMapping.class).get();
    }
}
