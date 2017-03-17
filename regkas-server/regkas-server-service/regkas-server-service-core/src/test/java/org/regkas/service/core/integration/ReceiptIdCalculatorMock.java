package org.regkas.service.core.integration;

import javax.enterprise.inject.Alternative;

import org.regkas.repository.api.values.ReceiptId;
import org.regkas.service.core.receipt.ReceiptIdCalculator;

@Alternative
public class ReceiptIdCalculatorMock implements ReceiptIdCalculator {

    private ReceiptId nextReceiptId;

    @Override
    public ReceiptId getNextReceiptId() {
        return nextReceiptId;
    }

    public void setNextReceiptId(ReceiptId nextReceiptId) {
        this.nextReceiptId = nextReceiptId;
    }
}
