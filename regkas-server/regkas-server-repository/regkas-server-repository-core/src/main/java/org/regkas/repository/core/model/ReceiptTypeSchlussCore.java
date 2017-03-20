package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeSchlussEntity;
import org.regkas.repository.api.model.ReceiptTypeSchluss;
import org.regkas.repository.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.repository.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.repository.core.turnovercounter.UpdateTurnoverCounterNothing;

public class ReceiptTypeSchlussCore extends ReceiptTypeCore<ReceiptTypeSchluss, ReceiptTypeSchlussEntity> implements ReceiptTypeSchluss {

    public ReceiptTypeSchlussCore(ReceiptTypeSchlussEntity receiptType) {
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
