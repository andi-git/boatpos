package org.regkas.service.core.util;

import org.regkas.model.ReceiptTypeEntity;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.service.api.bean.BillBean;

/**
 * Representation of receipt-type. <br> <br><b>Warning</b>: must fit with the database-entries!
 */
public enum ReceiptType {

    Start("Start-Beleg"),
    Standard("Standard-Beleg"),
    Storno("Storno-Beleg"),
    Training("Training-Beleg"),
    Null("Null-Beleg");

    private String receiptName;

    ReceiptType(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public static ReceiptType get(String receiptName) {
        ReceiptType result = ReceiptType.Null;
        for (ReceiptType receiptType : values()) {
            if (receiptType.getReceiptName().equals(receiptName)) {
                result = receiptType;
                break;
            }
        }
        return result;
    }
}