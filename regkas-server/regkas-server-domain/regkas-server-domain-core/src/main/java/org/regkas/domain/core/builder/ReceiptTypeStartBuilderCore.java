package org.regkas.domain.core.builder;

import org.regkas.model.ReceiptTypeStartEntity;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.ReceiptTypeStart;
import org.regkas.domain.core.model.ReceiptTypeStartCore;

import javax.enterprise.context.Dependent;

@Dependent
public class ReceiptTypeStartBuilderCore extends ReceiptTypeBuilderCore<ReceiptTypeStartBuilderCore, ReceiptTypeStart, ReceiptTypeStartCore, ReceiptTypeStartEntity> {

    @Override
    public ReceiptType build() {
        return null;
    }

    @Override
    public ReceiptType build(ReceiptTypeStartEntity receiptTypeEntity) {
        return new ReceiptTypeStartCore(receiptTypeEntity);
    }
}
