package org.regkas.repository.core.mapping;

import org.boatpos.common.repository.core.mapping.Mapping;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.model.ReceiptElementEntity;
import org.regkas.model.TaxSetEntity;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.TaxSetBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Mapping between {@link ReceiptElementEntity} and {@link ReceiptElementBean}.
 */
@Dependent
public class ReceiptElementMapping extends Mapping<ReceiptElementEntity, ReceiptElementBean> {

    @Inject
    @Current
    private EntityManager entityManager;

    public static ReceiptElementMapping fromCDI() {
        return CDI.current().select(ReceiptElementMapping.class).get();
    }
}
