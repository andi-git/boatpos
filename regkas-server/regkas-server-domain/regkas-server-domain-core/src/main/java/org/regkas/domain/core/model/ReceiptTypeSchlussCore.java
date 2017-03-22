package org.regkas.domain.core.model;

import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;
import org.regkas.model.ReceiptTypeSchlussEntity;
import org.regkas.domain.api.model.ReceiptTypeSchluss;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;

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
