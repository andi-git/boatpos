package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeTrainingEntity;
import org.regkas.repository.api.model.ReceiptType;

public class ReceiptTypeTrainingCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeTrainingEntity> {

    public ReceiptTypeTrainingCore(ReceiptTypeTrainingEntity receiptType) {
        super(receiptType);
    }
}