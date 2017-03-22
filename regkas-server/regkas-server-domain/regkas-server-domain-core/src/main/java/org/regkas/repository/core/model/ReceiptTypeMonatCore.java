package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeMonatEntity;
import org.regkas.repository.api.model.ReceiptTypeMonat;
import org.regkas.repository.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.repository.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.repository.core.turnovercounter.UpdateTurnoverCounterNothing;

public class ReceiptTypeMonatCore extends ReceiptTypeCore<ReceiptTypeMonat, ReceiptTypeMonatEntity> implements ReceiptTypeMonat {

    public ReceiptTypeMonatCore(ReceiptTypeMonatEntity receiptType) {
        super(receiptType);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterNothing.class);
    }

    @Override
    public EncryptTurnoverCounter getEncryptTurnoverCounter() {
        return fromCDI(EncryptTurnoverCounterDefault.class);
    }
}
