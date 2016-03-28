package org.regkas.repository.core.model;

import org.junit.Test;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.core.builder.ProductBuilderCoreTest;
import org.regkas.repository.core.builder.ProductGroupBuilderCoreTest;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ProductCoreTest {

    @Test
    public void testGetter() {
        Product product = ProductBuilderCoreTest.build();
        assertEquals("product-name", product.getName().get());
        assertEquals(BigDecimal.ONE, product.getPrice().get());
        assertEquals("productgroup-name", product.getProductGroup().getName().get());
    }
}