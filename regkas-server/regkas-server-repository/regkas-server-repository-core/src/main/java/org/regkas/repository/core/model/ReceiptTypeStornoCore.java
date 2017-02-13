package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeStornoEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.updateturnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.model.updateturnovercounter.UpdateTurnoverCounterSubtract;

public class ReceiptTypeStornoCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeStornoEntity> {

    public ReceiptTypeStornoCore(ReceiptTypeStornoEntity receiptType) {
        super(receiptType);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterSubtract.class);
    }

}