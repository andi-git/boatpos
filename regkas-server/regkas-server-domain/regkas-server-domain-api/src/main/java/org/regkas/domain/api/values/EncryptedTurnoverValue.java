package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The encrypted turnover-value of a receipt.
 */
public class EncryptedTurnoverValue extends SimpleValueObject<EncryptedTurnoverValue, String> {

    public EncryptedTurnoverValue(String value) {
        super(value);
    }
}
