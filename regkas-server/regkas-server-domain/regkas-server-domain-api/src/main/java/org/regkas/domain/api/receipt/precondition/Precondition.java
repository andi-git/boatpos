package org.regkas.domain.api.receipt.precondition;

import org.regkas.domain.api.model.CashBox;
import org.regkas.service.api.bean.BillBean;

public interface Precondition {

    boolean isFulfilled(CashBox cashBox);

    void fulfill(BillBean billBean, CashBox cashbox);

    int priority();
}
