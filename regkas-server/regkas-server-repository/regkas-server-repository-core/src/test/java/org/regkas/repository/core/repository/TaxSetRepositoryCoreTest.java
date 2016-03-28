package org.regkas.repository.core.repository;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.TaxSetRepository;
import org.regkas.repository.api.values.Name;
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
        assertEquals("Satz-Normal", taxSetRepository.loadBy(new Name("Satz-Normal")).get().getName().get());
    }
}
