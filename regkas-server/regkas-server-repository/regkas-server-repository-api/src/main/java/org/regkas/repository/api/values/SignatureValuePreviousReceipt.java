package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The signed value of the previous receipt.
 */
public class SignatureValuePreviousReceipt extends SimpleValueObject<SignatureValuePreviousReceipt, String> {

    public SignatureValuePreviousReceipt(String value) {
        super(value);
    }
}
