package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleBooleanObject;

public class LastReceiptMandatory extends SimpleBooleanObject<LastReceiptMandatory> {

    public static LastReceiptMandatory TRUE = new LastReceiptMandatory(true);

    public static LastReceiptMandatory FALSE = new LastReceiptMandatory(false);

    public LastReceiptMandatory(Boolean value) {
        super(value);
    }
}
