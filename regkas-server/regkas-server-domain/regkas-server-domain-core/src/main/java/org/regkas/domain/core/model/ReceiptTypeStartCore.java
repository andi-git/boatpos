package org.regkas.domain.core.model;

import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.model.ReceiptTypeStartEntity;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.ReceiptTypeStart;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.api.values.InputForChainCalculation;
import org.regkas.domain.api.values.LastReceiptMandatory;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;

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
