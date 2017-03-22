package org.regkas.domain.core.builder;

import org.regkas.model.ReceiptTypeMonatEntity;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.ReceiptTypeMonat;
import org.regkas.domain.core.model.ReceiptTypeMonatCore;

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
