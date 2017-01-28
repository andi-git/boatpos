package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeMonatEntity;
import org.regkas.repository.api.model.ReceiptType;

public class ReceiptTypeMonatCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeMonatEntity> {

    public ReceiptTypeMonatCore(ReceiptTypeMonatEntity receiptType) {
        super(receiptType);
    }
}