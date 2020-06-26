package org.regkas.domain.core.builder;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.TaxSet;
import org.regkas.domain.api.repository.TaxSetRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class TaxSetCoronaBuilderCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private TaxSetCoronaProducer taxSetCoronaProducer;

    @Test
    @Transactional
    public void testBuild() throws Exception {
        assertNull(new TaxSetCoronaBuilderCore().build());
        assertEquals("Satz-Corona", taxSetCoronaProducer.getTaxSet().getName().get());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Dependent
    public static class TaxSetCoronaProducer {

        @Inject
        private TaxSetRepository taxSetRepository;

        public TaxSet getTaxSet() {
            return taxSetRepository.loadBy(new Name("Satz-Corona")).get();
        }
    }
}
