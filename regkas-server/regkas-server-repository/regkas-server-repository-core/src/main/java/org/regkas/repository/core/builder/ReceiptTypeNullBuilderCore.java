package org.regkas.repository.core.builder;

import org.regkas.model.ReceiptTypeNullEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.ReceiptTypeNull;
import org.regkas.repository.core.model.ReceiptTypeNullCore;

import javax.enterprise.context.Dependent;

@Dependent
public class ReceiptTypeNullBuilderCore extends ReceiptTypeBuilderCore<ReceiptTypeNullBuilderCore, ReceiptTypeNull, ReceiptTypeNullCore, ReceiptTypeNullEntity> {

    @Override
    public ReceiptType build() {
        return null;
    }

    @Override
    public ReceiptType build(ReceiptTypeNullEntity receiptTypeEntity) {
        return new ReceiptTypeNullCore(receiptTypeEntity);
    }
}
