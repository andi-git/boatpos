package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeTrainingEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.updateturnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.model.updateturnovercounter.UpdateTurnoverCounterNothing;

public class ReceiptTypeTrainingCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeTrainingEntity> {

    public ReceiptTypeTrainingCore(ReceiptTypeTrainingEntity receiptType) {
        super(receiptType);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterNothing.class);
    }

}