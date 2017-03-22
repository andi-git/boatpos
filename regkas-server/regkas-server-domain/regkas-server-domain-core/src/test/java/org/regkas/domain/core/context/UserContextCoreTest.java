package org.regkas.domain.core.context;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.repository.UserRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class UserContextCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private UserContextCore userContext;

    @Inject
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testUserContext() throws Exception {
        assertNull(userContext.get());
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        assertNotNull(userContext.get());

        userContext.clear();
        assertNull(userContext.get());
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")).get());
        assertNotNull(userContext.get());
    }
}