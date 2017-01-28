package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeStornoEntity;
import org.regkas.repository.api.model.ReceiptType;

public class ReceiptTypeStornoCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeStornoEntity> {

    public ReceiptTypeStornoCore(ReceiptTypeStornoEntity receiptType) {
        super(receiptType);
    }
}