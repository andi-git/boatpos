package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeNullEntity;
import org.regkas.repository.api.model.ReceiptTypeNull;
import org.regkas.repository.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.repository.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.repository.core.turnovercounter.UpdateTurnoverCounterNothing;

public class ReceiptTypeNullCore extends ReceiptTypeCore<ReceiptTypeNull, ReceiptTypeNullEntity> implements ReceiptTypeNull {

    public ReceiptTypeNullCore(ReceiptTypeNullEntity receiptType) {
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
