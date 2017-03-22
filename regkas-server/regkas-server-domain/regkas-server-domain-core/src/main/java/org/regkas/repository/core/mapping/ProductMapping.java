package org.regkas.repository.core.mapping;

import org.boatpos.common.repository.core.mapping.Mapping;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.model.ProductEntity;
import org.regkas.service.api.bean.ProductBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Mapping between {@link ProductEntity} and {@link ProductBean}.
 */
@Dependent
public class ProductMapping extends Mapping<ProductEntity, ProductBean> {

    @Inject
    @Current
    private EntityManager entityManager;

    public static ProductMapping fromCDI() {
        return CDI.current().select(ProductMapping.class).get();
    }
}
