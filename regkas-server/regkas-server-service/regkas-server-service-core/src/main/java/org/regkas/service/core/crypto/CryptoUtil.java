package org.regkas.service.core.crypto;

import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.regkas.service.core.util.EncodingHelper;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

@ApplicationScoped
public class CryptoUtil {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private EncodingHelper encodingHelper;

    @PostConstruct
    private void init() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public boolean isUnlimitedStrengthPolicyAvailable() {
        try {
            return Cipher.getMaxAllowedKeyLength("AES") >= 256;
        } catch (NoSuchAlgorithmException e) {
            log.error(e);
        }
        return false;
    }

    public AESKey createAESKey() {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            int keySize = 256;
            kgen.init(keySize);
            SecretKey secretKey = kgen.generateKey();
            return new AESKey(secretKey, encodingHelper.base64Encode(secretKey.getEncoded(), false));
        } catch (final NoSuchAlgorithmException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    public static class AESKey {

        private final SecretKey secretKey;

        private final String base64EncodedString;

        private AESKey(SecretKey secretKey, String base64EncodedString) {
            this.secretKey = secretKey;
            this.base64EncodedString = base64EncodedString;
        }

        public String asBase64EncodedString() {
            return base64EncodedString;
        }

        public byte[] asByteArray() {
            return secretKey.getEncoded();
        }
    }
}
