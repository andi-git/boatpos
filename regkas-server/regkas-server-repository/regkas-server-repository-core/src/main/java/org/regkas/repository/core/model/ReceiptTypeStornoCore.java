package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeStornoEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.repository.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.turnovercounter.EncryptTurnoverCounterStorno;
import org.regkas.repository.core.turnovercounter.UpdateTurnoverCounterAdd;

public class ReceiptTypeStornoCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeStornoEntity> {

    public ReceiptTypeStornoCore(ReceiptTypeStornoEntity receiptType) {
        super(receiptType);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterAdd.class);
    }

    @Override
    public EncryptTurnoverCounter getEncryptTurnoverCounter() {
        return fromCDI(EncryptTurnoverCounterStorno.class);
    }
}
