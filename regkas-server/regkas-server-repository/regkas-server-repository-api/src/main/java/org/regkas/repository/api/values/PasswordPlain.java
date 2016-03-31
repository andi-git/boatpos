package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A password.
 */
public class PasswordPlain extends SimpleValueObject<PasswordPlain, String> {

    public PasswordPlain(String value) {
        super(value);
    }

    public String asMD5() {
        try {
            return new String(MessageDigest.getInstance("MD5").digest(value.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
