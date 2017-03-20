package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeSammelEntity;
import org.regkas.repository.api.model.ReceiptTypeSammel;
import org.regkas.repository.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.repository.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.repository.core.turnovercounter.UpdateTurnoverCounterNothing;

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
