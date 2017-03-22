package org.regkas.domain.core.builder;

import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.ProductGroup;
import org.regkas.domain.api.repository.TaxSetRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ProductGroupBuilderCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductGroupProducer productGroupProducer;

    @Test
    @Transactional
    public void testBuild() throws Exception {
        assertEquals("productgroup-name", productGroupProducer.getProductGroup().getName().get());
    }

    @Dependent
    public static class ProductGroupProducer {

        @Inject
        private TaxSetRepository taxSetRepository;

        @Inject
        private ProductBuilderCoreTest.ProductProducer productProducer;

        public ProductGroup getProductGroup() {
            return new ProductGroupBuilderCore()
                    .add(Enabled.TRUE)
                    .add(new KeyBinding(' '))
                    .add(new PictureUrl(""))
                    .add(new PictureUrlThumb(""))
                    .add(new Priority(1))
                    .add(new Name("productgroup-name"))
                    .add(taxSetRepository.loadBy(new Name("Satz-Normal")).get())
                    .add(CashBoxBuilderCoreTest.build())
                    .add(productProducer.getProduct())
                    .build();
        }
    }
}