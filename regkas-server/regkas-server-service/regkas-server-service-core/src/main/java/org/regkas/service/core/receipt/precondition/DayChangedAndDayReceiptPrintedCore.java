package org.regkas.service.core.receipt.precondition;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.receipt.precondition.DayChangedAndDayReceiptPrinted;
import org.regkas.service.api.ReceiptService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.core.receipt.ReceiptCreator;

@Dependent
public class DayChangedAndDayReceiptPrintedCore implements DayChangedAndDayReceiptPrinted {

    @Inject
    private ReceiptService receiptService;

    @Inject
    private ReceiptCreator receiptCreator;

    @Override
    public boolean isFulfilled(CashBox cashBox) {
        // TODO implement this
        return true;
        // return receiptService.shouldCreateDayReceipt();
    }

    @Override
    public void fulfill(BillBean billBean, CashBox cashbox) {
        // TODO implement this in future
    }

    @Override
    public int priority() {
        return 40;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
