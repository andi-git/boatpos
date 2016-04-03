package org.regkas.repository.core.repository;

import org.apache.commons.codec.digest.DigestUtils;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.PasswordPlain;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class UserRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testLoadByName() throws NoSuchAlgorithmException {
        assertEquals(DigestUtils.sha1Hex("abc123"), userRepository.loadBy(new Name("Maria Musterfrau")).get().getPassword().get());
    }

    @Test
    @Transactional
    public void testLoadAll() throws NoSuchAlgorithmException {
        assertEquals(3, userRepository.loadAll().size());
    }

    @Test
    @Transactional
    public void authenticate() throws Exception {
        assertTrue(userRepository.authenticate(new Name("Maria Musterfrau"), new PasswordPlain("abc123")).isPresent());
        assertFalse(userRepository.authenticate(new Name("Maria"), new PasswordPlain("abc123")).isPresent());
        assertFalse(userRepository.authenticate(new Name("Maria Musterfrau"), new PasswordPlain("abc")).isPresent());
    }
}
