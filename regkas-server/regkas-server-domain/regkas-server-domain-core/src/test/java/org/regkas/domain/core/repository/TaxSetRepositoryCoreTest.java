package org.regkas.domain.core.repository;

import org.boatpos.common.domain.api.values.Enabled;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.repository.TaxSetRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class TaxSetRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private TaxSetRepository taxSetRepository;

    @Test
    @Transactional
    public void testLoadByName() {
        assertEquals("Satz-Null", taxSetRepository.loadBy(new Name("Satz-Null")).get().getName().get());
        assertEquals("Satz-Normal", taxSetRepository.loadBy(new Name("Satz-Normal")).get().getName().get());
        assertEquals("Satz-Corona", taxSetRepository.loadBy(new Name("Satz-Corona")).get().getName().get());
    }

    @Test
    @Transactional
    public void testLoadAll() {
        assertEquals(6, taxSetRepository.loadAll().size());
    }

    @Test
    @Transactional
    public void testLoadAllEnabledDisable() {
        assertEquals(6, taxSetRepository.loadAll(Enabled.TRUE).size());
        assertEquals(0, taxSetRepository.loadAll(Enabled.FALSE).size());
    }
}
