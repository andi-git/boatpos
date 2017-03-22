package org.regkas.domain.core.model;

import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterStorno;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterAdd;
import org.regkas.model.ReceiptTypeStornoEntity;
import org.regkas.domain.api.model.ReceiptTypeStorno;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;

public class ReceiptTypeStornoCore extends ReceiptTypeCore<ReceiptTypeStorno, ReceiptTypeStornoEntity> implements ReceiptTypeStorno {

    public ReceiptTypeStornoCore(ReceiptTypeStornoEntity receiptType) {
        super(receiptType);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterAdd.class);
    }

    @Override
    public EncryptTurnoverCounter getEncryptTurnoverCounter() {
        return fromCDI(EncryptTurnoverCounterStorno.class);
    }
}
