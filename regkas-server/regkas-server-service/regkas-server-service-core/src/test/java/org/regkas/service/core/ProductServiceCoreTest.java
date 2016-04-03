package org.regkas.service.core;

import org.boatpos.common.service.api.EnabledState;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.service.api.ProductService;
import org.regkas.service.core.context.CompanyContext;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ProductServiceCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductService productService;

    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private CompanyContext companyContext;

    @Test
    @Transactional
    public void testGetProductForCurrentCompanyByName() {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        assertEquals("Cola", productService.getForCurrentCompany("Cola").getName());
        companyContext.clear();
    }

    @Test
    @Transactional
    public void testGetAll() {
        assertEquals(19, productService.getAll().size());
    }

    @Test
    @Transactional
    public void testGetAllByCompany() {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        assertEquals(4, productService.getAllForCurrentCompany().size());
        companyContext.clear();
    }

    @Test
    @Transactional
    public void testGetAllByCompanyEnabledDisabled() {
        companyContext.set(companyRepository.loadBy(new Name("company")));
        assertEquals(4, productService.getAllForCurrentCompany(EnabledState.All).size());
        assertEquals(4, productService.getAllForCurrentCompany(EnabledState.Enabled).size());
        assertEquals(0, productService.getAllForCurrentCompany(EnabledState.Disabled).size());
        companyContext.clear();
    }
}