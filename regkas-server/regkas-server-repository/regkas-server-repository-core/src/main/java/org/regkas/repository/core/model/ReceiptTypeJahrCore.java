package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeJahrEntity;
import org.regkas.repository.api.model.ReceiptType;

public class ReceiptTypeJahrCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeJahrEntity> {

    public ReceiptTypeJahrCore(ReceiptTypeJahrEntity receiptType) {
        super(receiptType);
    }
}