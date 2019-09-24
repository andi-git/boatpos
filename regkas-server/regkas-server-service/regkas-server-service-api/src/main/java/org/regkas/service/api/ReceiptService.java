package org.regkas.service.api;

import org.regkas.service.api.bean.BillBean;

public interface ReceiptService {

    Boolean isStartReceiptCreated();

    Boolean isSchlussReceiptCreated();

    Boolean shouldCreateMonthReceipt();

    String getCurrentRkOnlineEnvironment();

    void setRkOnlineEnvironment(String environment);

    boolean shouldCreateDayReceipt();

    BillBean getReceiptById(String receiptId);
}
