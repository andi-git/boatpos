package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeStartEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.updateturnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.model.updateturnovercounter.UpdateTurnoverCounterNothing;

public class ReceiptTypeStartCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeStartEntity> {

    public ReceiptTypeStartCore(ReceiptTypeStartEntity receiptType) {
        super(receiptType);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterNothing.class);
    }
}