package org.regkas.service.core.receipt.precondition;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;

import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.receipt.precondition.Precondition;

@ApplicationScoped
public class PreconditionChecker {

    public List<Precondition> getOrderedPreconditions(ReceiptType receiptType) {
        List<Precondition> preconditions = new ArrayList<>();
        @SuppressWarnings("unchecked") List<Class<? extends Precondition>> preconditionTypes = receiptType.getPreconditions();
        for (Class<? extends Precondition> preconditionType : preconditionTypes) {
            preconditions.add(fromCDI(preconditionType));
        }
        return preconditions;
    }

    public boolean isFulfilled(ReceiptType receiptType, CashBox cashBox, Class<? extends Precondition> preconditionType) {
        Precondition precondition = fromCDI(preconditionType);
        return !needsToBeFulfilled(receiptType, precondition) || precondition.isFulfilled(cashBox);
    }

    private boolean needsToBeFulfilled(ReceiptType receiptType, Precondition precondition) {
        return getOrderedPreconditions(receiptType).contains(precondition);
    }

    private <T extends Precondition> T fromCDI(Class<T> preconditionType) {
        return CDI.current().select(preconditionType).get();
    }
}
