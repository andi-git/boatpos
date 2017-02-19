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
public class TaxSetErmaessigt2BuilderCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private TaxSetErmaessigt2Producer taxSetErmaessigt2Producer;

    @Test
    @Transactional
    public void testBuild() throws Exception {
        assertNull(new TaxSetErmaessigt2BuilderCore().build());
        assertEquals("Satz-Ermaessigt-2", taxSetErmaessigt2Producer.getTaxSet().getName().get());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Dependent
    public static class TaxSetErmaessigt2Producer {

        @Inject
        private TaxSetRepository taxSetRepository;

        public TaxSet getTaxSet() {
            return taxSetRepository.loadBy(new Name("Satz-Ermaessigt-2")).get();
        }
    }
}