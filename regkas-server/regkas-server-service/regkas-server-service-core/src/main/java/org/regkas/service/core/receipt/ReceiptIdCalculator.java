package org.regkas.service.core.receipt;

import org.regkas.domain.api.values.ReceiptId;

public interface ReceiptIdCalculator {

    ReceiptId getNextReceiptId();
}
