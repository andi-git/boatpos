package org.regkas.repository.core.mapping;

import org.boatpos.common.repository.core.mapping.Mapping;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.model.CompanyEntity;
import org.regkas.service.api.bean.CompanyBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Mapping between {@link CompanyEntity} and {@link CompanyBean}.
 */
@Dependent
public class CompanyMapping extends Mapping<CompanyEntity, CompanyBean> {

    @Inject
    @Current
    private EntityManager entityManager;

    public static CompanyMapping fromCDI() {
        return CDI.current().select(CompanyMapping.class).get();
    }
}
