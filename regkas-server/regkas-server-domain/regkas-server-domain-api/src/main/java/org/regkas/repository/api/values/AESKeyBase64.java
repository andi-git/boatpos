package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * An AES-Key as {@link String}, encoded in Base64.
 */
public class AESKeyBase64 extends SimpleValueObject<AESKeyBase64, String> {

    public AESKeyBase64(String value) {
        super(value);
    }
}
