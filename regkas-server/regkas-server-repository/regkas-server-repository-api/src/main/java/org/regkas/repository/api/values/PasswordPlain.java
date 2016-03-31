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
}
