package org.regkas.repository.core.builder;

import org.regkas.model.ReceiptTypeStandardEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.ReceiptTypeStandard;
import org.regkas.repository.core.model.ReceiptTypeStandardCore;

import javax.enterprise.context.Dependent;

@Dependent
public class ReceiptTypeStandardBuilderCore extends ReceiptTypeBuilderCore<ReceiptTypeStandardBuilderCore, ReceiptTypeStandard, ReceiptTypeStandardCore, ReceiptTypeStandardEntity> {

    @Override
    public ReceiptType build() {
        return null;
    }

    @Override
    public ReceiptType build(ReceiptTypeStandardEntity receiptTypeEntity) {
        return new ReceiptTypeStandardCore(receiptTypeEntity);
    }
}
