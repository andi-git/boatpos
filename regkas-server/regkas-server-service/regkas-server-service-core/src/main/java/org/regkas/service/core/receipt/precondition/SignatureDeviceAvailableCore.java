package org.regkas.service.core.receipt.precondition;

import java.util.Optional;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.receipt.precondition.SignatureDeviceAvailable;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.service.api.bean.BillBean;

@Dependent
public class SignatureDeviceAvailableCore implements SignatureDeviceAvailable {

    @Inject
    private ReceiptRepository receiptRepository;

    @Override
    public boolean isFulfilled(CashBox cashBox) {
        Optional<Receipt> receipt = receiptRepository.loadLastReceipt(cashBox);
        return receipt.isPresent() && receipt.get().getSignatureDeviceAvailable().get();
    }

    @Override
    public void fulfill(BillBean billBean, CashBox cashbox) {
        throw new RuntimeException("signature divice is not available; can't change that");
    }

    @Override
    public int priority() {
        return 30;
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
