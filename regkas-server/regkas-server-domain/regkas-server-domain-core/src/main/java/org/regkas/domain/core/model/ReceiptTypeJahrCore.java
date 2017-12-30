package org.regkas.domain.core.model;

import com.google.common.collect.Lists;
import org.regkas.domain.api.receipt.precondition.Precondition;
import org.regkas.domain.api.receipt.precondition.SchlussReceiptNotAvailable;
import org.regkas.domain.api.receipt.precondition.SignatureDeviceAvailable;
import org.regkas.domain.api.receipt.precondition.StartReceiptAvailable;
import org.regkas.model.ReceiptTypeJahrEntity;
import org.regkas.domain.api.model.ReceiptTypeJahr;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;

import java.util.List;

public class ReceiptTypeJahrCore extends ReceiptTypeCore<ReceiptTypeJahr, ReceiptTypeJahrEntity> implements ReceiptTypeJahr {

    public ReceiptTypeJahrCore(ReceiptTypeJahrEntity receiptType) {
        super(receiptType);
    }

    @Override
    public List<Class<? extends Precondition>> getPreconditions() {
        return Lists.newArrayList(StartReceiptAvailable.class, SignatureDeviceAvailable.class, SchlussReceiptNotAvailable.class);
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
