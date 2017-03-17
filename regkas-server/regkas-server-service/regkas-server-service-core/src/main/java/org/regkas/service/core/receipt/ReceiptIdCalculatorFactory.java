package org.regkas.service.core.receipt;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ReceiptIdCalculatorFactory {

    @Inject
    private ReceiptIdCalculator defaultReceiptIdCalculator;

    private ReceiptIdCalculator currentReceiptIdCalculator;

    @PostConstruct
    public void reset() {
        currentReceiptIdCalculator = defaultReceiptIdCalculator;
    }

    public void setReceiptIdCalculator(ReceiptIdCalculator receiptIdCalculator) {
        currentReceiptIdCalculator = receiptIdCalculator;
    }

    public ReceiptIdCalculator get() {
        return currentReceiptIdCalculator;
    }
}
