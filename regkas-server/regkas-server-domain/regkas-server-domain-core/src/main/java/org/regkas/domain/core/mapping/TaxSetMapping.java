package org.regkas.domain.core.mapping;

import org.boatpos.common.domain.core.mapping.Mapping;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.model.TaxSetEntity;
import org.regkas.service.api.bean.TaxSetBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Mapping between {@link TaxSetEntity} and {@link TaxSetBean}.
 */
@Dependent
public class TaxSetMapping extends Mapping<TaxSetEntity, TaxSetBean> {

    @Inject
    @Current
    private EntityManager entityManager;

    public static TaxSetMapping fromCDI() {
        return CDI.current().select(TaxSetMapping.class).get();
    }
}
