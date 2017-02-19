package org.regkas.repository.core.builder;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.repository.TaxSetRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class TaxSetNullBuilderCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private TaxSetNullProducer taxSetNullProducer;

    @Test
    @Transactional
    public void testBuild() throws Exception {
        assertNull(new TaxSetNullBuilderCore().build());
        assertEquals("Satz-Null", taxSetNullProducer.getTaxSet().getName().get());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Dependent
    public static class TaxSetNullProducer {

        @Inject
        private TaxSetRepository taxSetRepository;

        public TaxSet getTaxSet() {
            return taxSetRepository.loadBy(new Name("Satz-Null")).get();
        }
    }
}