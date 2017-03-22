package org.regkas.domain.core.repository;

import org.boatpos.common.domain.api.values.Enabled;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.ProductGroupRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ProductGroupRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductGroupRepository productGroupRepository;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Test
    @Transactional
    public void testLoadByNameAndCompany() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals("Snack", productGroupRepository.loadBy(new Name("Snack"), cashBox).get().getName().get());
    }

    @Test
    @Transactional
    public void testLoadByCompany() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals(7, productGroupRepository.loadBy(cashBox).size());
        assertEquals(7, productGroupRepository.loadBy(cashBox, Enabled.TRUE).size());
        assertEquals(0, productGroupRepository.loadBy(cashBox, Enabled.FALSE).size());
    }

}
