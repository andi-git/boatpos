package org.regkas.repository.core.mapping;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.ProductRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.service.api.bean.ProductBean;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class ProductMappingTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private ProductMapping productMapping;

    @Test
    @Transactional
    public void testFromCDI() {
        assertNotNull(ProductMapping.fromCDI());
    }

    @Test
    @Transactional
    public void testMappingEntityToDto() {
        Optional<Product> product = productRepository.loadBy(new Name("Cola"), cashBoxRepository.loadBy(new Name("RegKas1")).get());
        ProductBean productBean = productMapping.mapEntity(product.get().asEntity());
        assertEquals("Cola", productBean.getName());
    }
}