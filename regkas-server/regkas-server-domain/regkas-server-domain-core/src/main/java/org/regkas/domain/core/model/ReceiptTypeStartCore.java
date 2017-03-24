package org.regkas.domain.core.model;

import java.util.List;

import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.ReceiptTypeStart;
import org.regkas.domain.api.receipt.precondition.Precondition;
import org.regkas.domain.api.receipt.precondition.SignatureDeviceAvailable;
import org.regkas.domain.api.receipt.precondition.StartReceiptNotAvailable;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.api.values.InputForChainCalculation;
import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;
import org.regkas.model.ReceiptTypeStartEntity;

import com.google.common.collect.Lists;

public class ReceiptTypeStartCore extends ReceiptTypeCore<ReceiptTypeStart, ReceiptTypeStartEntity> implements ReceiptTypeStart {

    public ReceiptTypeStartCore(ReceiptTypeStartEntity receiptType) {
        super(receiptType);
    }

    @Override
    public List<Class<? extends Precondition>> getPreconditions() {
        return Lists.newArrayList(StartReceiptNotAvailable.class, SignatureDeviceAvailable.class);
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
}
