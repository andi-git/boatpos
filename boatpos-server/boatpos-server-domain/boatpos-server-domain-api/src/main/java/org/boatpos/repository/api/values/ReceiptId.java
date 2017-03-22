package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The id of the receipt.
 */
public class ReceiptId extends SimpleValueObject<ReceiptId, String> {

    public ReceiptId(String value) {
        super(value);
    }
}
