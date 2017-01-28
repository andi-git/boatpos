package org.regkas.repository.core.builder;

import org.regkas.model.ReceiptTypeStornoEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.ReceiptTypeStorno;
import org.regkas.repository.core.model.ReceiptTypeStornoCore;

import javax.enterprise.context.Dependent;

@Dependent
public class ReceiptTypeStornoBuilderCore extends ReceiptTypeBuilderCore<ReceiptTypeStornoBuilderCore, ReceiptTypeStorno, ReceiptTypeStornoCore, ReceiptTypeStornoEntity> {

    @Override
    public ReceiptType build() {
        return null;
    }

    @Override
    public ReceiptType build(ReceiptTypeStornoEntity receiptTypeEntity) {
        return new ReceiptTypeStornoCore(receiptTypeEntity);
    }
}
