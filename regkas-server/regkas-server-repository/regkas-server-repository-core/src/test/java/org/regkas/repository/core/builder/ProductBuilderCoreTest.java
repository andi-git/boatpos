package org.regkas.repository.core.builder;

import org.boatpos.common.repository.api.values.Enabled;
import org.boatpos.common.repository.api.values.KeyBinding;
import org.boatpos.common.repository.api.values.PictureUrl;
import org.boatpos.common.repository.api.values.PictureUrlThumb;
import org.boatpos.common.repository.api.values.Priority;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.values.Generic;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Price;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ProductBuilderCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductBuilderCoreTest.ProductProducer productProducer;

    @Test
    @Transactional
    public void testBuild() throws Exception {
        assertEquals("product-name", productProducer.getProduct().getName().get());
    }

    @Dependent
    public static class ProductProducer {

        @Inject
        private TaxSetNormalBuilderCoreTest.TaxSetNormalProducer taxSetNormalProducer;

        public Product getProduct() {
            return new ProductBuilderCore()
                    .add(Enabled.TRUE)
                    .add(new KeyBinding(' '))
                    .add(new PictureUrl(""))
                    .add(new PictureUrlThumb(""))
                    .add(new Priority(1))
                    .add(new Name("product-name"))
                    .add(new Price(BigDecimal.ONE))
                    .add(new ProductGroupBuilderCore()
                            .add(Enabled.TRUE)
                            .add(new KeyBinding(' '))
                            .add(new PictureUrl(""))
                            .add(new PictureUrlThumb(""))
                            .add(new Priority(1))
                            .add(new Name("productgroup-name"))
                            .add(taxSetNormalProducer.getTaxSet())
                            .add(CashBoxBuilderCoreTest.build())
                            .build())
                    .add(Generic.FALSE)
                    .build();
        }
    }
}