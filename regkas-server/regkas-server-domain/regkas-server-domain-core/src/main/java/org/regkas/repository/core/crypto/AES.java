package org.regkas.repository.core.crypto;

import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.repository.api.values.AESKeyBase64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@ApplicationScoped
public class AES {

    public static final int NUMBER_OF_BYTES = 3;

    @Inject
    private Crypto crypto;

    @Inject
    private Encoding encoding;

    @Inject
    private Crypto.MessageDigestSHA256 messageDigestSHA256;

    @Inject
    @SLF4J
    private LogWrapper log;

    public AESKey createAESKey() {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            int keySize = 256;
            kgen.init(keySize);
            SecretKey secretKey = kgen.generateKey();
            return new AESKey(secretKey, encoding.base64Encode(secretKey.getEncoded(), false));
        } catch (final NoSuchAlgorithmException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    public SecretKey convertBase64KeyToSecretKey(final String base64AESKey) {
        final byte[] rawAesKey = encoding.base64Decode(base64AESKey, false);
        return new SecretKeySpec(rawAesKey, "AES");
    }

    public String calcCheckSumFromKey(String base64AESKey) {
        //calculate SHA256 hash of AES key
        MessageDigest md = messageDigestSHA256.get();
        md.update(base64AESKey.getBytes());
        byte[] hashValue = md.digest();

        //extract N bytes from hash (N=3)
        byte[] hashValueBytes = new byte[NUMBER_OF_BYTES];
        System.arraycopy(hashValue, 0, hashValueBytes, 0, NUMBER_OF_BYTES);

        //BASE64-encode check sum
        String base64ValueValidation = encoding.base64Encode(hashValueBytes, false);

        //remove padding character "="
        base64ValueValidation = base64ValueValidation.replace("=", "");
        return base64ValueValidation;
    }

    public static class AESKey {

        private final SecretKey secretKey;

        private final AESKeyBase64 aesKeyBase64;

        public AESKey(AESKeyBase64 aesKeyBase64) {
            this.secretKey = CDI.current().select(AES.class).get().convertBase64KeyToSecretKey(aesKeyBase64.get());
            this.aesKeyBase64 = aesKeyBase64;
        }

        public AESKey(SecretKey secretKey, String base64EncodedString) {
            this.secretKey = secretKey;
            this.aesKeyBase64 = new AESKeyBase64(base64EncodedString);
        }

        public AESKeyBase64 asBase64Encoded() {
            return aesKeyBase64;
        }

        public byte[] asByteArray() {
            return secretKey.getEncoded();
        }

        public SecretKey asSecretKey() {
            return secretKey;
        }
    }
}
