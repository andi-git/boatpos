package org.regkas.service.core.crypto;

import org.regkas.service.core.util.EncodingHelper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@ApplicationScoped
public class AESCheckSum {

    public static final int NUMBER_OF_BYTES = 3;

    @Inject
    private CryptoUtil cryptoUtil;

    @Inject
    private EncodingHelper encodingHelper;

    public String calcCheckSumFromKey(String base64AESKey) throws NoSuchAlgorithmException {
        //calculate SHA256 hash of AES key
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(base64AESKey.getBytes());
        byte[] hashValue = md.digest();

        //extract N bytes from hash (N=3)
        byte[] hashValueBytes = new byte[NUMBER_OF_BYTES];
        System.arraycopy(hashValue, 0, hashValueBytes, 0, NUMBER_OF_BYTES);

        //BASE64-encode check sum
        String base64ValueValidation = encodingHelper.base64Encode(hashValueBytes, false);

        //remove padding character "="
        base64ValueValidation = base64ValueValidation.replace("=", "");
        return base64ValueValidation;
    }
}
