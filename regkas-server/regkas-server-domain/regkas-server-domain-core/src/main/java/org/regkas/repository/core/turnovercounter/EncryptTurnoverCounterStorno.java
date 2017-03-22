package org.regkas.repository.core.turnovercounter;

import org.regkas.repository.core.crypto.Encoding;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.repository.api.values.EncryptedTurnoverValue;
import org.regkas.repository.api.values.ReceiptId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EncryptTurnoverCounterStorno implements EncryptTurnoverCounter {

    @Inject
    private Encoding encoding;

    @Override
    public EncryptedTurnoverValue encryptTurnoverCounter(ReceiptId receiptId, CashBox cashBox) {
        return new EncryptedTurnoverValue(encoding.base64Encode("STO".getBytes(), false));
    }
}
