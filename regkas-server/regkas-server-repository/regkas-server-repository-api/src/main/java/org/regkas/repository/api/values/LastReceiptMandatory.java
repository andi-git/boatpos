package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleBooleanObject;

public class LastReceiptMandatory extends SimpleBooleanObject<LastReceiptMandatory> {

    public static LastReceiptMandatory TRUE = new LastReceiptMandatory(true);

    public static LastReceiptMandatory FALSE = new LastReceiptMandatory(false);

    public LastReceiptMandatory(Boolean value) {
        super(value);
    }
}
