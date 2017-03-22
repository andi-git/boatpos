package org.regkas.domain.core.builder;

import org.regkas.model.ReceiptTypeNullEntity;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.ReceiptTypeNull;
import org.regkas.domain.core.model.ReceiptTypeNullCore;

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
