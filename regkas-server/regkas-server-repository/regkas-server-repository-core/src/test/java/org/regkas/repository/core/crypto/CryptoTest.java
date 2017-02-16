package org.regkas.repository.core.crypto;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class CryptoTest {

    @Inject
    private Crypto crypto;

    @Inject
    private Encoding encoding;

    @Test
    public void isUnlimitedStrengthPolicyAvailable() throws Exception {
        assertTrue(crypto.isUnlimitedStrengthPolicyAvailable());
    }
}