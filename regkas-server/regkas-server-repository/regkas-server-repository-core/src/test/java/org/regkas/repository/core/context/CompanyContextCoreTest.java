package org.regkas.repository.core.context;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class CompanyContextCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private CompanyContextCore companyContext;

    @Inject
    private CompanyRepository companyRepository;

    @Test
    @Transactional
    public void testCompanyContext() throws Exception {
        assertNull(companyContext.get());
        companyContext.set(companyRepository.loadBy(new Name("company")));
        assertNotNull(companyContext.get());

        companyContext.clear();
        assertNull(companyContext.get());
        companyContext.set(companyRepository.loadBy(new Name("company")).get());
        assertNotNull(companyContext.get());
    }
}