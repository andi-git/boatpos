package org.regkas.domain.core.model;

import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.model.ReceiptTypeMonatEntity;
import org.regkas.domain.api.model.ReceiptTypeMonat;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;

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
