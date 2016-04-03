package org.regkas.repository.core.repository;

import org.boatpos.common.repository.api.values.Enabled;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.repository.ProductGroupRepository;
import org.regkas.repository.api.repository.ProductRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ProductRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductGroupRepository productGroupRepository;

    @Inject
    private CompanyRepository companyRepository;

    @Test
    @Transactional
    public void testLoadByNameAndCompany() {
        Company company = companyRepository.loadBy(new Name("company")).get();
        assertEquals("Cola", productRepository.loadBy(new Name("Cola"), company).get().getName().get());
    }

    @Test
    @Transactional
    public void testLoadGenericByProductGroup() {
        Optional<ProductGroup> productGroup = productGroupRepository.loadBy(new Name("Snack"), companyRepository.loadBy(new Name("company")).get());
        assertEquals("Snack", productRepository.loadGenericBy(productGroup.get()).get().getName().get());
    }

    @Test
    @Transactional
    public void testLoadByProductGroup() {
        Optional<ProductGroup> productGroup = productGroupRepository.loadBy(new Name("Snack"), companyRepository.loadBy(new Name("company")).get());
        assertEquals(2, productRepository.loadBy(productGroup.get()).size());
    }

    @Test
    @Transactional
    public void testLoadByCompany() {
        Company company = companyRepository.loadBy(new Name("company")).get();
        assertEquals(4, productRepository.loadBy(company).size());
        assertEquals(4, productRepository.loadBy(company, Enabled.TRUE).size());
        assertEquals(0, productRepository.loadBy(company, Enabled.FALSE).size());
    }
}
