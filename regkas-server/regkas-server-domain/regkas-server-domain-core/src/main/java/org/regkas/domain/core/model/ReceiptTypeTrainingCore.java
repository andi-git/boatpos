package org.regkas.domain.core.model;

import java.util.List;

import org.regkas.domain.api.model.ReceiptTypeTraining;
import org.regkas.domain.api.receipt.precondition.DayChangedAndDayReceiptPrinted;
import org.regkas.domain.api.receipt.precondition.MonthChangedAndMonthReceiptPrinted;
import org.regkas.domain.api.receipt.precondition.Precondition;
import org.regkas.domain.api.receipt.precondition.StartReceiptAvailable;
import org.regkas.domain.api.turnovercounter.EncryptTurnoverCounter;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.core.turnovercounter.EncryptTurnoverCounterTraining;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;
import org.regkas.model.ReceiptTypeTrainingEntity;

import com.google.common.collect.Lists;

public class ReceiptTypeTrainingCore extends ReceiptTypeCore<ReceiptTypeTraining, ReceiptTypeTrainingEntity> implements ReceiptTypeTraining {

    public ReceiptTypeTrainingCore(ReceiptTypeTrainingEntity receiptType) {
        super(receiptType);
    }

    @Override
    public List<Class<? extends Precondition>> getPreconditions() {
        return Lists.newArrayList(StartReceiptAvailable.class, MonthChangedAndMonthReceiptPrinted.class, DayChangedAndDayReceiptPrinted.class);
    }

    @Override
    public UpdateTurnoverCounter getUpdateTurnoverCounter() {
        return fromCDI(UpdateTurnoverCounterNothing.class);
    }

    @Override
    public EncryptTurnoverCounter getEncryptTurnoverCounter() {
        return fromCDI(EncryptTurnoverCounterTraining.class);
    }
}
