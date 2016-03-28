package org.regkas.repository.core.model;

import org.junit.Test;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.core.builder.ProductGroupBuilderCoreTest;
import org.regkas.repository.core.builder.TaxSetBuilderCoreTest;

import static org.junit.Assert.assertEquals;

public class ProductGroupCoreTest {

    @Test
    public void testGetter() {
        ProductGroup productGroup = ProductGroupBuilderCoreTest.build();
        assertEquals("productgroup-name", productGroup.getName().get());
        assertEquals("taxset-name", productGroup.getTaxSet().getName().get());
    }
}