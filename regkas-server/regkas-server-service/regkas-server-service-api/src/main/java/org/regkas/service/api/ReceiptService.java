package org.regkas.service.api;

public interface ReceiptService {

    Boolean isStartReceiptCreated();

    Boolean isSchlussReceiptCreated();

    Boolean shouldCreateMonthReceipt();

    String getCurrentRkOnlineEnvironment();

    void setRkOnlineEnvironment(String environment);

    boolean shouldCreateDayReceipt();
}
