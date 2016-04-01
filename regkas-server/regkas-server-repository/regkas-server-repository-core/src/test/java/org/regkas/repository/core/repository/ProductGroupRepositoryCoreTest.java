package org.regkas.repository.core.repository;

import org.boatpos.common.repository.api.values.Enabled;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.repository.ProductGroupRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.builder.CashBoxBuilderCore;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ProductGroupRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductGroupRepository productGroupRepository;

    @Inject
    private CompanyRepository companyRepository;

    @Test
    @Transactional
    public void testLoadByName() {
        assertEquals("Snack", productGroupRepository.loadBy(new Name("Snack")).get().getName().get());
    }

    @Test
    @Transactional
    public void testLoadByNameAndCompany() {
        Company company = companyRepository.loadBy(new Name("company")).get();
        assertEquals("Snack", productGroupRepository.loadBy(new Name("Snack"), company).get().getName().get());
    }

    @Test
    @Transactional
    public void testLoadByCompany() {
        Company company = companyRepository.loadBy(new Name("company")).get();
        assertEquals(7, productGroupRepository.loadBy(company).size());
        assertEquals(7, productGroupRepository.loadBy(company, Enabled.TRUE).size());
        assertEquals(0, productGroupRepository.loadBy(company, Enabled.FALSE).size());
    }

}
