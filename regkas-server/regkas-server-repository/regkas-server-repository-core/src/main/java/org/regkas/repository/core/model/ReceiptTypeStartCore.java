package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeStartEntity;
import org.regkas.repository.api.model.ReceiptType;

public class ReceiptTypeStartCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeStartEntity> {

    public ReceiptTypeStartCore(ReceiptTypeStartEntity receiptType) {
        super(receiptType);
    }
}