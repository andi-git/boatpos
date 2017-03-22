package org.regkas.domain.core.repository;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.User;
import org.regkas.domain.api.repository.CompanyRepository;
import org.regkas.domain.api.repository.UserRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class CompanyRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testLoadByName() {
        assertEquals("atu123", companyRepository.loadBy(new Name("company")).get().getATU().get());
    }

    @Test
    @Transactional
    public void testLoadByUser() {
        User user = userRepository.loadBy(new Name("Maria Musterfrau")).get();
        assertEquals("atu123", companyRepository.loadBy(user).get().getATU().get());
    }
}
