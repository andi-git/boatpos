package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeMonatEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.updateturnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.model.updateturnovercounter.UpdateTurnoverCounterNothing;

public class ReceiptTypeMonatCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeMonatEntity> {

    public ReceiptTypeMonatCore(ReceiptTypeMonatEntity receiptType) {
        super(receiptType);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterNothing.class);
    }

}