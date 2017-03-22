package org.regkas.domain.core.model;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.Product;
import org.regkas.domain.core.builder.ProductBuilderCoreTest;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Arquillian.class)
public class ProductCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductBuilderCoreTest.ProductProducer productProducer;

    @Test
    @Transactional
    public void testGetter() {
        Product product = productProducer.getProduct();
        assertEquals("product-name", product.getName().get());
        assertEquals(BigDecimal.ONE, product.getPrice().get());
        assertEquals("productgroup-name", product.getProductGroup().getName().get());
        assertFalse(product.isGeneric().get());
    }
}