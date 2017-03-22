package org.boatpos.service.core.util;

import org.apache.commons.codec.binary.Base64;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.api.values.DayId;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A decryption and encryption which is compatible with PHP.
 */
@ApplicationScoped
public class MyRentalCrypt {

    @Inject
    @SLF4J
    private LogWrapper log;

    private Cipher cipherToEncrypt;

    private Cipher cipherToDecrypt;

    @PostConstruct
    private void init() throws RuntimeException {
        SecretKeySpec skey = new SecretKeySpec(System.getProperty("boatpos.myrental.key").getBytes(), "AES");
        try {
            cipherToEncrypt = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipherToEncrypt.init(Cipher.ENCRYPT_MODE, skey);
            cipherToDecrypt = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipherToDecrypt.init(Cipher.DECRYPT_MODE, skey);
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    public String encrypt(String input) {
        byte[] crypted;
        try {
            crypted = cipherToEncrypt.doFinal(input.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new String(Base64.encodeBase64(crypted));
    }

    public String encryptHtmlSafe(String input) {
        try {
            return URLEncoder.encode(encrypt(input), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String decrypt(String input) {
        byte[] output;
        try {
            output = cipherToDecrypt.doFinal(Base64.decodeBase64(input));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new String(output);
    }

    public String decryptHtmlSafe(String input) {
        try {
            return decrypt(URLDecoder.decode(input, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String encrypt(Rental rental) {
        checkNotNull(rental, "'rental' must not be null");
        return encrypt(rental.getDay().get().format(DateTimeFormatter.ISO_LOCAL_DATE) + "_" + pp(rental.getDayId()));
    }

    public String encryptHtmlSafe(Rental rental) {
        checkNotNull(rental, "'rental' must not be null");
        return encryptHtmlSafe(rental.getDay().get().format(DateTimeFormatter.ISO_LOCAL_DATE) + "_" + pp(rental.getDayId()));
    }

    private String pp(DayId dayId) {
        Integer id = dayId.get();
        StringBuilder result = new StringBuilder();
        if (id < 1000) {
            result.append("0");
        }
        if (id < 100) {
            result.append("0");
        }
        if (id < 10) {
            result.append("0");
        }
        result.append(id);
        return result.toString();
    }
}
