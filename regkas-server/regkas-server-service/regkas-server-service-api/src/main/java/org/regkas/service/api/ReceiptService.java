package org.regkas.service.api;

/**
 * Service for receipts.
 */
public interface ReceiptService {

    /**
     * Check if the start-receipt was created.
     *
     * @return {@code true} if the start-receipt was created
     */
    Boolean isStartReceiptCreated();

    /**
     * Check if the month-receipt for the last month should be created.
     *
     * @return {@code true} if the month-receipt for the last month should be created
     */
    Boolean shouldCreateMonthReceipt();

    String getCurrentRkOnlineEnvironment();

    void setRkOnlineEnvironment(String environment);
}
