package org.regkas.repository.core.model;

import org.regkas.model.ReceiptTypeStandardEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.values.Name;

public class ReceiptTypeStandardCore extends ReceiptTypeCore<ReceiptType, ReceiptTypeStandardEntity> {

    public ReceiptTypeStandardCore(ReceiptTypeStandardEntity receiptType) {
        super(receiptType);
    }
}