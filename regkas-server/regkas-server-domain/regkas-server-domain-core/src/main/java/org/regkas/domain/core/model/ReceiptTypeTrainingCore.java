package org.regkas.domain.core.model;

import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterTraining;
import org.regkas.model.ReceiptTypeTrainingEntity;
import org.regkas.domain.api.model.ReceiptTypeTraining;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;

public class ReceiptTypeTrainingCore extends ReceiptTypeCore<ReceiptTypeTraining, ReceiptTypeTrainingEntity> implements ReceiptTypeTraining {

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
