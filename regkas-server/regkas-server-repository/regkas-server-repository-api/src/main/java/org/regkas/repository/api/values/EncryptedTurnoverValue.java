package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The encrypted turnover-value of a receipt.
 */
public class EncryptedTurnoverValue extends SimpleValueObject<EncryptedTurnoverValue, String> {

    public EncryptedTurnoverValue(String value) {
        super(value);
    }
}
