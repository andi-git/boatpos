package org.regkas.domain.core.model;

import com.google.common.collect.Lists;
import org.regkas.domain.api.receipt.precondition.Precondition;
import org.regkas.domain.api.receipt.precondition.SchlussReceiptNotAvailable;
import org.regkas.domain.api.receipt.precondition.SignatureDeviceAvailable;
import org.regkas.domain.api.receipt.precondition.StartReceiptAvailable;
import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;
import org.regkas.model.ReceiptTypeSchlussEntity;
import org.regkas.domain.api.model.ReceiptTypeSchluss;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;

import java.util.List;

public class ReceiptTypeSchlussCore extends ReceiptTypeCore<ReceiptTypeSchluss, ReceiptTypeSchlussEntity> implements ReceiptTypeSchluss {

    public ReceiptTypeSchlussCore(ReceiptTypeSchlussEntity receiptType) {
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
