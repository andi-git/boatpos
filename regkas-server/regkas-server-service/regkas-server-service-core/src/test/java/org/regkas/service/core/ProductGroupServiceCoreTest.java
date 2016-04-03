package org.regkas.service.core;

import org.boatpos.common.service.api.EnabledState;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.service.api.ProductGroupService;
import org.regkas.service.core.context.CompanyContext;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ProductGroupServiceCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductGroupService productGroupService;

    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private CompanyContext companyContext;

    @Test
    @Transactional
    public void testGetProductForCurrentCompanyByName() {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        assertEquals("Snack", productGroupService.getForCurrentCompany("Snack").getName());
        companyContext.clear();
    }

    @Test
    @Transactional
    public void testGetGenericProductFor() {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        assertEquals("Snack", productGroupService.getGenericProductFor("Snack").getName());
        companyContext.clear();
    }

    @Test
    @Transactional
    public void testGetAll() {
        assertEquals(17, productGroupService.getAll().size());
    }

    @Test
    @Transactional
    public void testGetAllByCompany() {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        assertEquals(7, productGroupService.getAllForCurrentCompany().size());
        companyContext.clear();
    }

    @Test
    @Transactional
    public void testGetAllByCompanyEnabledDisabled() {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        assertEquals(7, productGroupService.getAllForCurrentCompany(EnabledState.All).size());
        assertEquals(7, productGroupService.getAllForCurrentCompany(EnabledState.Enabled).size());
        assertEquals(0, productGroupService.getAllForCurrentCompany(EnabledState.Disabled).size());
        companyContext.clear();
    }
}