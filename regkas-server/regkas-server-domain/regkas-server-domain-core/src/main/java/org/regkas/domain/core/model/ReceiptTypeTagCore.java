package org.regkas.domain.core.model;

import java.util.List;

import org.regkas.domain.api.model.ReceiptTypeTag;
import org.regkas.domain.api.receipt.precondition.Precondition;
import org.regkas.domain.api.receipt.precondition.SchlussReceiptNotAvailable;
import org.regkas.domain.api.receipt.precondition.StartReceiptAvailable;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;
import org.regkas.model.ReceiptTypeTagEntity;

import com.google.common.collect.Lists;

public class ReceiptTypeTagCore extends ReceiptTypeCore<ReceiptTypeTag, ReceiptTypeTagEntity> implements ReceiptTypeTag {

    public ReceiptTypeTagCore(ReceiptTypeTagEntity receiptType) {
        super(receiptType);
    }

    @Override
    public List<Class<? extends Precondition>> getPreconditions() {
        return Lists.newArrayList(StartReceiptAvailable.class, SchlussReceiptNotAvailable.class);
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
