package org.regkas.service.core.receipt;

import org.regkas.repository.api.values.ReceiptId;

public interface ReceiptIdCalculator {

    ReceiptId getNextReceiptId();
}
