package org.regkas.service.core.receipt.precondition;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.receipt.precondition.StartReceiptNotAvailable;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.Period;

@Dependent
public class StartReceiptNotAvailableCore implements StartReceiptNotAvailable {

    @Inject
    private ReceiptRepository receiptRepository;

    @Override
    public boolean isFulfilled(CashBox cashBox) {
        return !receiptRepository.loadLatestWithReceiptTypeStart(cashBox).isPresent();
    }

    @Override
    public void fulfill(BillBean billBean, CashBox cashbox) {
        throw new RuntimeException("start-receipt is not available; will not print start-receipt automatically");
    }

    @Override
    public int priority() {
        return 20;
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
