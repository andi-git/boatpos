package org.regkas.repository.core.repository;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.builder.CashBoxBuilderCore;
import org.regkas.repository.core.builder.UserBuilderCore;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class UserRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testLoadByName() {
        assertEquals("abc123", userRepository.loadBy(new Name("Maria Musterfrau")).get().getPassword().get());
    }
}
