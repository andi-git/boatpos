package org.regkas.domain.core.mapping;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.Company;
import org.regkas.domain.api.repository.CompanyRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.service.api.bean.CompanyBean;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class CompanyMappingTest extends EntityManagerProviderForRegkas {

    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private CompanyMapping companyMapping;

    @Test
    @Transactional
    public void testFromCDI() {
        assertNotNull(CompanyMapping.fromCDI());
    }

    @Test
    @Transactional
    public void testMappingEntityToDto() {
        Optional<Company> company = companyRepository.loadBy(new Name("company"));
        CompanyBean companyBean = companyMapping.mapEntity(company.get().asEntity());
        assertEquals("company", companyBean.getName());
        assertEquals("Street 1", companyBean.getStreet());
    }
}