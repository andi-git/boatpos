package org.boatpos.common.repository.core.boat;

import org.boatpos.common.repository.core.mapping.Mapping;
import org.boatpos.common.util.qualifiers.Current;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Dependent
public class BoatMapping extends Mapping<BoatEntity, BoatBean> {

    @Inject
    @Current
    private EntityManager entityManager;

    public static BoatMapping fromCDI() {
        return CDI.current().select(BoatMapping.class).get();
    }
}
