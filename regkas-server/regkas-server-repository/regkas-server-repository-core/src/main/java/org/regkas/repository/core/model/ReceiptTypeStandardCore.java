package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeStandardEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.updateturnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.model.updateturnovercounter.UpdateTurnoverCounterAdd;

public class ReceiptTypeStandardCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeStandardEntity> {

    public ReceiptTypeStandardCore(ReceiptTypeStandardEntity receiptType) {
        super(receiptType);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterAdd.class);
    }

}