package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeJahrEntity;
import org.regkas.repository.api.model.ReceiptTypeJahr;
import org.regkas.repository.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.repository.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.repository.core.turnovercounter.UpdateTurnoverCounterNothing;

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
