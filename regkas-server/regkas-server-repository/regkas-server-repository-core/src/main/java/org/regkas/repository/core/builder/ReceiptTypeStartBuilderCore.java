package org.regkas.repository.core.builder;

import org.regkas.model.ReceiptTypeStartEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.ReceiptTypeStandard;
import org.regkas.repository.api.model.ReceiptTypeStart;
import org.regkas.repository.core.model.ReceiptTypeStartCore;

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
