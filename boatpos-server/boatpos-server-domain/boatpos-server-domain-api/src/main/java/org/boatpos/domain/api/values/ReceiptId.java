package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The id of the receipt.
 */
public class ReceiptId extends SimpleValueObject<ReceiptId, String> {

    public ReceiptId(String value) {
        super(value);
    }
}
