package org.regkas.domain.core.model;

import java.util.List;

import org.regkas.domain.api.model.ReceiptTypeStorno;
import org.regkas.domain.api.receipt.precondition.DayChangedAndDayReceiptPrinted;
import org.regkas.domain.api.receipt.precondition.MonthChangedAndMonthReceiptPrinted;
import org.regkas.domain.api.receipt.precondition.Precondition;
import org.regkas.domain.api.receipt.precondition.SchlussReceiptNotAvailable;
import org.regkas.domain.api.receipt.precondition.StartReceiptAvailable;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterStorno;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterAdd;
import org.regkas.model.ReceiptTypeStornoEntity;

import com.google.common.collect.Lists;

public class ReceiptTypeStornoCore extends ReceiptTypeCore<ReceiptTypeStorno, ReceiptTypeStornoEntity> implements ReceiptTypeStorno {

    public ReceiptTypeStornoCore(ReceiptTypeStornoEntity receiptType) {
        super(receiptType);
    }

    @Override
    public List<Class<? extends Precondition>> getPreconditions() {
        return Lists.newArrayList(StartReceiptAvailable.class, MonthChangedAndMonthReceiptPrinted.class, DayChangedAndDayReceiptPrinted.class, SchlussReceiptNotAvailable.class);
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
