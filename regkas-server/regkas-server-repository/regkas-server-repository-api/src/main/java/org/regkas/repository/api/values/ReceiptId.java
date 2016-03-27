package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The id of a receipt.
 */
public class ReceiptId extends SimpleValueObject<ReceiptId, String> {

    public ReceiptId(String value) {
        super(value);
    }
}
