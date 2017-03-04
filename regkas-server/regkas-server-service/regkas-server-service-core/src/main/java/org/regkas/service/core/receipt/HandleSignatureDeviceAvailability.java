package org.regkas.service.core.receipt;

import org.regkas.repository.api.model.Receipt;
import org.regkas.service.api.bean.BillBean;

public interface HandleSignatureDeviceAvailability {

    boolean canHandle(Receipt currentReceipt, Receipt lastReceipt);

    BillBean handle(BillBean billBean);
}