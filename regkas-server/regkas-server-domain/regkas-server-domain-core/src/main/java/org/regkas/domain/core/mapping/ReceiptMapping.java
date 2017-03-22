package org.regkas.domain.core.mapping;

import org.boatpos.common.domain.core.mapping.Mapping;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.model.ReceiptEntity;
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
