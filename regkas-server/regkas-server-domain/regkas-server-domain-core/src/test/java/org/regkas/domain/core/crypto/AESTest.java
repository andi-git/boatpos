package org.regkas.domain.core.crypto;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class AESTest {

    @Inject
    private AES aes;

    @Inject
    private Encoding encoding;

    @Test
    public void testCalcCheckSumFromKey() throws Exception {
        String base64AESKey = "1sU3dcuQudXTqgHUblaZFRGv0Hd0XDWc2c3hq2ngjwM=";
        assertEquals("5UcV", aes.calcCheckSumFromKey(base64AESKey));
    }

    @Test
    public void createAESKey() throws Exception {
        AES.AESKey aesKey = aes.createAESKey();
        assertEquals(32, aesKey.asByteArray().length);
        assertEquals(44, aesKey.asBase64Encoded().get().length());
//        for (int i=0; i<10; i++) {
//            System.out.println(aes.createAESKey().asBase64Encoded().get());
//        }
    }

}