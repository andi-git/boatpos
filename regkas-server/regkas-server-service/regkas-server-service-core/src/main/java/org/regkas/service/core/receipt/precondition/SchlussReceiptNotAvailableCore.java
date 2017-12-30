package org.regkas.service.core.receipt.precondition;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.receipt.precondition.SchlussReceiptNotAvailable;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.service.api.bean.BillBean;

@Dependent
public class SchlussReceiptNotAvailableCore implements SchlussReceiptNotAvailable {

    @Inject
    private ReceiptRepository receiptRepository;

    @Override
    public boolean isFulfilled(CashBox cashBox) {
        return !receiptRepository.loadLatestWithReceiptTypeSchluss(cashBox).isPresent();
    }

    @Override
    public void fulfill(BillBean billBean, CashBox cashbox) {
        throw new RuntimeException("final-receipt is not available; will not print final-receipt automatically");
    }

    @Override
    public int priority() {
        return 1000;
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
