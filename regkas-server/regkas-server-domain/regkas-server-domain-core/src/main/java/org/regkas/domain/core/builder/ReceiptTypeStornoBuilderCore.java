package org.regkas.domain.core.builder;

import org.regkas.model.ReceiptTypeStornoEntity;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.ReceiptTypeStorno;
import org.regkas.domain.core.model.ReceiptTypeStornoCore;

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
