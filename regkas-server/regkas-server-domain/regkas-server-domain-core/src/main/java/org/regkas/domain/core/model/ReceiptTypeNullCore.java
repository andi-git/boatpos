package org.regkas.domain.core.model;

import java.util.List;

import org.regkas.domain.api.model.ReceiptTypeNull;
import org.regkas.domain.api.receipt.precondition.DayChangedAndDayReceiptPrinted;
import org.regkas.domain.api.receipt.precondition.MonthChangedAndMonthReceiptPrinted;
import org.regkas.domain.api.receipt.precondition.Precondition;
import org.regkas.domain.api.receipt.precondition.SchlussReceiptNotAvailable;
import org.regkas.domain.api.receipt.precondition.StartReceiptAvailable;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterDefault;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;
import org.regkas.model.ReceiptTypeNullEntity;

import com.google.common.collect.Lists;

public class ReceiptTypeNullCore extends ReceiptTypeCore<ReceiptTypeNull, ReceiptTypeNullEntity> implements ReceiptTypeNull {

    public ReceiptTypeNullCore(ReceiptTypeNullEntity receiptType) {
        super(receiptType);
    }

    @Override
    public List<Class<? extends Precondition>> getPreconditions() {
        return Lists.newArrayList(StartReceiptAvailable.class, MonthChangedAndMonthReceiptPrinted.class, DayChangedAndDayReceiptPrinted.class, SchlussReceiptNotAvailable.class);
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
