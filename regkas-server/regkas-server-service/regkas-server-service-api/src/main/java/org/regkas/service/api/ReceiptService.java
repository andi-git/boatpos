package org.regkas.service.api;

public interface ReceiptService {

    Boolean isStartReceiptCreated();

    Boolean shouldCreateMonthReceipt();

    String getCurrentRkOnlineEnvironment();

    void setRkOnlineEnvironment(String environment);

    boolean shouldCreateDayReceipt();
}
