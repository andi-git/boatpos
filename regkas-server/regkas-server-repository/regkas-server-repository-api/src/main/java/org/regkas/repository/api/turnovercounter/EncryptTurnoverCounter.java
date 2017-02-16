package org.regkas.repository.api.turnovercounter;

import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.values.EncryptedTurnoverValue;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.repository.api.values.TotalPrice;

/**
 * Encrypt the turnover-count for a {@link CashBox} by a {@link TotalPrice}.
 */
public interface EncryptTurnoverCounter {

    EncryptedTurnoverValue encryptTurnoverCounter(ReceiptId receiptId, CashBox cashBox);
}
