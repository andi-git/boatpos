package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeStartEntity;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.ReceiptTypeStart;
import org.regkas.repository.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.repository.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.api.values.InputForChainCalculation;
import org.regkas.repository.api.values.LastReceiptMandatory;
import org.regkas.repository.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.repository.core.turnovercounter.UpdateTurnoverCounterNothing;

public class ReceiptTypeStartCore extends ReceiptTypeCore<ReceiptTypeStart, ReceiptTypeStartEntity> implements ReceiptTypeStart {

    public ReceiptTypeStartCore(ReceiptTypeStartEntity receiptType) {
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

    @Override
    public InputForChainCalculation getInputForChainCalculation(CashBox cashBox) {
        return new InputForChainCalculation(cashBox.getName().get());
    }

    @Override
    public LastReceiptMandatory isLastReceiptMandatory() {
        return LastReceiptMandatory.FALSE;
    }
}