package org.regkas.service.core.crypto;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.core.util.EncodingHelper;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class CryptoUtilTest {

    @Inject
    private CryptoUtil cryptoUtil;

    @Inject
    private EncodingHelper encodingHelper;

    @Test
    public void isUnlimitedStrengthPolicyAvailable() throws Exception {
        assertTrue(cryptoUtil.isUnlimitedStrengthPolicyAvailable());
    }

    @Test
    public void createAESKey() throws Exception {
        CryptoUtil.AESKey aesKey = cryptoUtil.createAESKey();
        assertEquals(32, aesKey.asByteArray().length);
        assertEquals(44, aesKey.asBase64EncodedString().length());
//        for (int i=0; i<10; i++) {
//            System.out.println(cryptoUtil.createAESKey().asBase64EncodedString());
//        }
    }
}