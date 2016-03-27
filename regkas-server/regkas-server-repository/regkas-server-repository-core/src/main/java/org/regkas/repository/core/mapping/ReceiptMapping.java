package org.regkas.repository.core.mapping;

import org.boatpos.common.repository.core.mapping.Mapping;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.model.CompanyEntity;
import org.regkas.model.ReceiptEntity;
import org.regkas.service.api.bean.CompanyBean;
import org.regkas.service.api.bean.ReceiptBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Mapping between {@link ReceiptEntity} and {@link ReceiptBean}.
 */
@Dependent
public class ReceiptMapping extends Mapping<ReceiptEntity, ReceiptBean> {

    @Inject
    @Current
    private EntityManager entityManager;

    public static ReceiptMapping fromCDI() {
        return CDI.current().select(ReceiptMapping.class).get();
    }
}
