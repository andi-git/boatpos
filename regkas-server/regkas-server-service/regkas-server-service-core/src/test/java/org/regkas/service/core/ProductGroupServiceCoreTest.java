package org.regkas.service.core;

import org.boatpos.common.service.api.EnabledState;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.service.api.ProductGroupService;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ProductGroupServiceCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductGroupService productGroupService;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Test
    @Transactional
    public void testGetProductForCurrentCompanyByName() {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        assertEquals("Snack", productGroupService.getForCurrentCompany("Snack").getName());
        cashBoxContext.clear();
    }

    @Test
    @Transactional
    public void testGetGenericProductFor() {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        assertEquals("Snack", productGroupService.getGenericProductFor("Snack").getName());
        cashBoxContext.clear();
    }

    @Test
    @Transactional
    public void testGetAll() {
        assertEquals(28, productGroupService.getAll().size());
    }

    @Test
    @Transactional
    public void testGetAllByCompany() {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        assertEquals(7, productGroupService.getAllForCurrentCompany().size());
        cashBoxContext.clear();
    }

    @Test
    @Transactional
    public void testGetAllByCompanyEnabledDisabled() {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        assertEquals(7, productGroupService.getAllForCurrentCompany(EnabledState.All).size());
        assertEquals(7, productGroupService.getAllForCurrentCompany(EnabledState.Enabled).size());
        assertEquals(0, productGroupService.getAllForCurrentCompany(EnabledState.Disabled).size());
        cashBoxContext.clear();
    }
}
