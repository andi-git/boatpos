package org.regkas.domain.core.model;

import org.regkas.model.ReceiptTypeNullEntity;
import org.regkas.domain.api.model.ReceiptTypeNull;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;

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
