package org.regkas.repository.core.repository;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.PasswordPlain;
import org.regkas.repository.core.builder.CashBoxBuilderCore;
import org.regkas.repository.core.builder.UserBuilderCore;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class UserRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testLoadByName() throws NoSuchAlgorithmException {
        assertEquals(new String(MessageDigest.getInstance("MD5").digest("abc123".getBytes())), userRepository.loadBy(new Name("Maria Musterfrau")).get().getPassword().get());
    }

    @Test
    @Transactional
    public void authenticate() throws Exception {
        assertTrue(userRepository.authenticate(new Name("Maria Musterfrau"), new PasswordPlain("abc123")));
        assertFalse(userRepository.authenticate(new Name("Maria"), new PasswordPlain("abc123")));
        assertFalse(userRepository.authenticate(new Name("Maria Musterfrau"), new PasswordPlain("abc")));
    }
}
