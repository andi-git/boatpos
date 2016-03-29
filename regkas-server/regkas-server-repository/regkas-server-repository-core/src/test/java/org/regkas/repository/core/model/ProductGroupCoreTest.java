package org.regkas.repository.core.model;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.core.builder.ProductBuilderCoreTest;
import org.regkas.repository.core.builder.ProductGroupBuilderCoreTest;

import static org.junit.Assert.assertEquals;

public class ProductGroupCoreTest {

    @Test
    public void testGetter() {
        ProductGroup productGroup = ProductGroupBuilderCoreTest.build();
        assertEquals("productgroup-name", productGroup.getName().get());
        assertEquals("taxset-name", productGroup.getTaxSet().getName().get());

        productGroup.clearProducts();
        productGroup.addProduct(ProductBuilderCoreTest.build());
        assertEquals(1, productGroup.getProducts().size());
        productGroup.clearProducts();
        productGroup.addProducts(Lists.newArrayList(ProductBuilderCoreTest.build()));
        assertEquals(1, productGroup.getProducts().size());

    }
}