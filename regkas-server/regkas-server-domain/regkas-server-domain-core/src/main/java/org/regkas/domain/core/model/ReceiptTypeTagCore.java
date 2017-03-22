package org.regkas.domain.core.model;

import org.regkas.model.ReceiptTypeTagEntity;
import org.regkas.domain.api.model.ReceiptTypeTag;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;

public class ReceiptTypeTagCore extends ReceiptTypeCore<ReceiptTypeTag, ReceiptTypeTagEntity> implements ReceiptTypeTag {

    public ReceiptTypeTagCore(ReceiptTypeTagEntity receiptType) {
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
