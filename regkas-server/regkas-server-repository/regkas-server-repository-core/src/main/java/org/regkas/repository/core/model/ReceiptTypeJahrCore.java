package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeJahrEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.updateturnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.model.updateturnovercounter.UpdateTurnoverCounterNothing;

public class ReceiptTypeJahrCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeJahrEntity> {

    public ReceiptTypeJahrCore(ReceiptTypeJahrEntity receiptType) {
        super(receiptType);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterNothing.class);
    }

}