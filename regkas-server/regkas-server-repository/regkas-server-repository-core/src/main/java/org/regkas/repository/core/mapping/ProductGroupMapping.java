package org.regkas.repository.core.mapping;

import org.boatpos.common.repository.core.mapping.Mapping;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.model.ProductGroupEntity;
import org.regkas.service.api.bean.ProductGroupBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.stream.Collectors;

/**
 * Mapping between {@link ProductGroupEntity} and {@link ProductGroupBean}.
 */
@Dependent
public class ProductGroupMapping extends Mapping<ProductGroupEntity, ProductGroupBean> {

    @Inject
    @Current
    private EntityManager entityManager;

    public static ProductGroupMapping fromCDI() {
        return CDI.current().select(ProductGroupMapping.class).get();
    }

    @Override
    public ProductGroupBean mapEntity(ProductGroupEntity entity) {
        ProductGroupBean productGroupBean = super.mapEntity(entity);
        productGroupBean.setProducts(productGroupBean.getProducts().stream().sorted((p1, p2) -> p1.getPriority().compareTo(p2.getPriority())).collect(Collectors.toList()));
        return productGroupBean;
    }
}
