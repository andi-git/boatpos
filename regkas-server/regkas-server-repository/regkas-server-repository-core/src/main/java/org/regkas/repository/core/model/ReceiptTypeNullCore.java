package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeNullEntity;
import org.regkas.repository.api.model.ReceiptType;

public class ReceiptTypeNullCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeNullEntity> {

    public ReceiptTypeNullCore(ReceiptTypeNullEntity receiptType) {
        super(receiptType);
    }
}