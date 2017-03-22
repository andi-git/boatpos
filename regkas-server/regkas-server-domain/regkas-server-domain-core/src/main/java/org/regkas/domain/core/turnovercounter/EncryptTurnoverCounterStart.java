package org.regkas.domain.core.turnovercounter;

import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.values.EncryptedTurnoverValue;
import org.regkas.domain.api.values.ReceiptId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EncryptTurnoverCounterStart implements EncryptTurnoverCounter {

    @Inject
    private EncryptTurnoverCounterDefault encryptTurnoverCounterDefault;

    @Override
    public EncryptedTurnoverValue encryptTurnoverCounter(ReceiptId receiptId, CashBox cashBox) {
        return encryptTurnoverCounterDefault.encryptTurnoverCounter(new ReceiptId(cashBox.getName().get()), cashBox);
    }
}
