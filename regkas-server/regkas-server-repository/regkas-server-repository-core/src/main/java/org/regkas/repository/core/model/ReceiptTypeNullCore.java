package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeNullEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.updateturnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.model.updateturnovercounter.UpdateTurnoverCounterNothing;

public class ReceiptTypeNullCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeNullEntity> {

    public ReceiptTypeNullCore(ReceiptTypeNullEntity receiptType) {
        super(receiptType);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterNothing.class);
    }

}