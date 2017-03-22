package org.regkas.domain.core.builder;

import org.regkas.domain.core.model.ReceiptTypeStandardCore;
import org.regkas.model.ReceiptTypeStandardEntity;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.ReceiptTypeStandard;

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
