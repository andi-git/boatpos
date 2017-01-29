package org.regkas.service.core.crypto;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.core.util.EncodingHelper;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class AESCheckSumTest {

    @Inject
    private AESCheckSum aesCheckSum;

    @Inject
    private CryptoUtil cryptoUtil;

    @Inject
    private EncodingHelper encodingHelper;

    @Test
    public void testCalcCheckSumFromKey() throws Exception {
        String base64AESKey = "1sU3dcuQudXTqgHUblaZFRGv0Hd0XDWc2c3hq2ngjwM=";
        assertEquals("5UcV", aesCheckSum.calcCheckSumFromKey(base64AESKey));
    }
}