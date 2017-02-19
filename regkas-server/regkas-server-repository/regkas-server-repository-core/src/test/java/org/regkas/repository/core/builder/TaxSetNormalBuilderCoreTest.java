package org.regkas.repository.core.builder;

import org.boatpos.common.repository.api.values.*;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.model.ProductGroupEntity;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.repository.TaxSetRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Price;
import org.regkas.repository.api.values.TaxPercent;
import org.regkas.repository.core.model.ProductGroupCore;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class TaxSetNormalBuilderCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private TaxSetNormalProducer taxSetNormalProducer;

    @Test
    @Transactional
    public void testBuild() throws Exception {
        assertNull(new TaxSetNormalBuilderCore().build());
        assertEquals("Satz-Normal", taxSetNormalProducer.getTaxSet().getName().get());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Dependent
    public static class TaxSetNormalProducer {

        @Inject
        private TaxSetRepository taxSetRepository;

        public TaxSet getTaxSet() {
            return taxSetRepository.loadBy(new Name("Satz-Normal")).get();
        }
    }
}