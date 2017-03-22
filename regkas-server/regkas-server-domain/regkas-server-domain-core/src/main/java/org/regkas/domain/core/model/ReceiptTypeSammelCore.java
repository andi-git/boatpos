package org.regkas.domain.core.model;

import org.regkas.model.ReceiptTypeSammelEntity;
import org.regkas.domain.api.model.ReceiptTypeSammel;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;

public class ReceiptTypeSammelCore extends ReceiptTypeCore<ReceiptTypeSammel, ReceiptTypeSammelEntity> implements ReceiptTypeSammel {

    public ReceiptTypeSammelCore(ReceiptTypeSammelEntity receiptType) {
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
