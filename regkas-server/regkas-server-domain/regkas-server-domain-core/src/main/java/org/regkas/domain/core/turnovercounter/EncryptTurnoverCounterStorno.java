package org.regkas.domain.core.turnovercounter;

import org.regkas.domain.core.crypto.Encoding;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.values.EncryptedTurnoverValue;
import org.regkas.domain.api.values.ReceiptId;

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
