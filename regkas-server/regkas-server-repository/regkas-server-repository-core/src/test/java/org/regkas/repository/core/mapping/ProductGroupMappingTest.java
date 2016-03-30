package org.regkas.repository.core.mapping;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.model.ProductGroupEntity;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.repository.ProductGroupRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.service.api.bean.ProductGroupBean;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class ProductGroupMappingTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductGroupRepository productGroupRepository;

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
        Optional<ProductGroup> productGroup = productGroupRepository.loadBy(new Name("Snack"));
        assertEquals(2, productGroup.get().getProducts().size());
        productGroup.get().getProducts().forEach(System.out::println);
        ProductGroupBean productGroupBean = productGroupMapping.mapEntity(productGroup.get().asEntity());
        assertEquals("Snack", productGroupBean.getName());
        assertEquals(2, productGroupBean.getProducts().size());
        productGroupBean.getProducts().forEach(System.out::println);
        assertTrue(productGroupBean.getProducts().get(0).getPriority() < productGroupBean.getProducts().get(1).getPriority());
    }
}