package org.regkas.repository.core.model;

import com.google.common.collect.Lists;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.core.builder.ProductBuilderCoreTest;
import org.regkas.repository.core.builder.ProductGroupBuilderCoreTest;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ProductGroupCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductBuilderCoreTest.ProductProducer productProducer;

    @Inject
    private ProductGroupBuilderCoreTest.ProductGroupProducer productGroupProducer;

    @Test
    @Transactional
    public void testGetter() {
        ProductGroup productGroup = productGroupProducer.getProductGroup();
        assertEquals("productgroup-name", productGroup.getName().get());
        assertEquals("Satz-Normal", productGroup.getTaxSet().getName().get());

        productGroup.clearProducts();
        productGroup.addProduct(productProducer.getProduct());
        assertEquals(1, productGroup.getProducts().size());
        productGroup.clearProducts();
        productGroup.addProducts(Lists.newArrayList(productProducer.getProduct()));
        assertEquals(1, productGroup.getProducts().size());

    }
}