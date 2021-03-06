package org.regkas.domain.core.mapping;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.ProductGroup;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.ProductGroupRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.service.api.bean.ProductGroupBean;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ProductGroupMappingTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductGroupRepository productGroupRepository;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private ProductGroupMapping productGroupMapping;

    @Test
    @Transactional
    public void testFromCDI() {
        assertNotNull(ProductGroupMapping.fromCDI());
    }

    @Test
    @Transactional
    public void testMappingEntityToDto() {
        Optional<ProductGroup> productGroup = productGroupRepository.loadBy(new Name("Snack"), cashBoxRepository.loadBy(new Name("RegKas1")).get());
        assertEquals(2, productGroup.get().getProducts().size());
        ProductGroupBean productGroupBean = productGroupMapping.mapEntity(productGroup.get().asEntity());
        assertEquals("Snack", productGroupBean.getName());
        assertEquals(2, productGroupBean.getProducts().size());
        assertTrue(productGroupBean.getProducts().get(0).getPriority() < productGroupBean.getProducts().get(1).getPriority());
    }
}