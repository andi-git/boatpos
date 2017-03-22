package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * The initialization-vector to encrypt the turnover-counter
 */
public class IVToEncryptTurnoverCounter extends SimpleValueObject<IVToEncryptTurnoverCounter, String> {

    public IVToEncryptTurnoverCounter(String value) {
        super(value);
    }

    public IVToEncryptTurnoverCounter(Name cashBoxName, ReceiptId receiptId) {
        this(combine(cashBoxName, receiptId));
    }

    private static String combine(Name cashBoxName, ReceiptId receiptId) {
        checkArgument(cashBoxName.isPresent(), "'cashBoxName' must be present");
        checkArgument(receiptId.isPresent(), "'receiptId' must be present");
        return cashBoxName.get() + receiptId.get();
    }
}
