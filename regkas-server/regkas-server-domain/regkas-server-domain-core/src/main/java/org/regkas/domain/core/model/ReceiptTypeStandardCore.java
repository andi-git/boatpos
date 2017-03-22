package org.regkas.domain.core.model;

import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterAdd;
import org.regkas.model.ReceiptTypeStandardEntity;
import org.regkas.domain.api.model.ReceiptTypeStandard;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;

public class ReceiptTypeStandardCore extends ReceiptTypeCore<ReceiptTypeStandard, ReceiptTypeStandardEntity> implements ReceiptTypeStandard {

    public ReceiptTypeStandardCore(ReceiptTypeStandardEntity receiptType) {
        super(receiptType);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterAdd.class);
    }

    @Override
    public EncryptTurnoverCounter getEncryptTurnoverCounter() {
        return fromCDI(EncryptTurnoverCounterDefault.class);
    }
}
