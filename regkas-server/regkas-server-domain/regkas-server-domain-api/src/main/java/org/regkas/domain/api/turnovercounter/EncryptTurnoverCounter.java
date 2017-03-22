package org.regkas.domain.api.turnovercounter;

import org.regkas.domain.api.values.EncryptedTurnoverValue;
import org.regkas.domain.api.values.TotalPrice;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.values.ReceiptId;

/**
 * Encrypt the turnover-count for a {@link CashBox} by a {@link TotalPrice}.
 */
public interface EncryptTurnoverCounter {

    EncryptedTurnoverValue encryptTurnoverCounter(ReceiptId receiptId, CashBox cashBox);
}
