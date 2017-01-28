package org.regkas.repository.core.builder;

import org.regkas.model.ReceiptTypeMonatEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.ReceiptTypeMonat;
import org.regkas.repository.core.model.ReceiptTypeMonatCore;

import javax.enterprise.context.Dependent;

@Dependent
public class ReceiptTypeMonatBuilderCore extends ReceiptTypeBuilderCore<ReceiptTypeMonatBuilderCore, ReceiptTypeMonat, ReceiptTypeMonatCore, ReceiptTypeMonatEntity> {

    @Override
    public ReceiptType build() {
        return null;
    }

    @Override
    public ReceiptType build(ReceiptTypeMonatEntity receiptTypeEntity) {
        return new ReceiptTypeMonatCore(receiptTypeEntity);
    }
}
