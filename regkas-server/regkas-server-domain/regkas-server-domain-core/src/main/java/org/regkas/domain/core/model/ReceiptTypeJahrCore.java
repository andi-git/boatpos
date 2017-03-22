package org.regkas.domain.core.model;

import org.regkas.model.ReceiptTypeJahrEntity;
import org.regkas.domain.api.model.ReceiptTypeJahr;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;

public class ReceiptTypeJahrCore extends ReceiptTypeCore<ReceiptTypeJahr, ReceiptTypeJahrEntity> implements ReceiptTypeJahr {

    public ReceiptTypeJahrCore(ReceiptTypeJahrEntity receiptType) {
        super(receiptType);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterNothing.class);
    }

    @Override
    public EncryptTurnoverCounter getEncryptTurnoverCounter() {
        return fromCDI(EncryptTurnoverCounterDefault.class);
    }
}
