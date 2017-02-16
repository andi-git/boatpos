package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeTrainingEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.repository.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.repository.core.turnovercounter.EncryptTurnoverCounterTraining;
import org.regkas.repository.core.turnovercounter.UpdateTurnoverCounterNothing;

public class ReceiptTypeTrainingCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeTrainingEntity> {

    public ReceiptTypeTrainingCore(ReceiptTypeTrainingEntity receiptType) {
        super(receiptType);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterNothing.class);
    }

    @Override
    public EncryptTurnoverCounter getEncryptTurnoverCounter() {
        return fromCDI(EncryptTurnoverCounterTraining.class);
    }
}