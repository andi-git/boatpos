package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeStandardEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.repository.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.repository.core.turnovercounter.UpdateTurnoverCounterAdd;

public class ReceiptTypeStandardCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeStandardEntity> {

    public ReceiptTypeStandardCore(ReceiptTypeStandardEntity receiptType) {
        super(receiptType);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterAdd.class);
    }

    @Override
    public EncryptTurnoverCounter getEncryptTurnoverCounter() {
        return fromCDI(EncryptTurnoverCounterDefault.class);
    }
}