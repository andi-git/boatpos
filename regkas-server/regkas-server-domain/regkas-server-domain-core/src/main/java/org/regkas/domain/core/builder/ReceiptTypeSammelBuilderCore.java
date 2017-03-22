package org.regkas.domain.core.builder;

import javax.enterprise.context.Dependent;

import org.regkas.domain.core.model.ReceiptTypeSammelCore;
import org.regkas.model.ReceiptTypeSammelEntity;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.ReceiptTypeSammel;

@Dependent
public class ReceiptTypeSammelBuilderCore extends ReceiptTypeBuilderCore<ReceiptTypeSammelBuilderCore, ReceiptTypeSammel, ReceiptTypeSammelCore, ReceiptTypeSammelEntity> {

    @Override
    public ReceiptType build() {
        return null;
    }

    @Override
    public ReceiptType build(ReceiptTypeSammelEntity receiptTypeEntity) {
        return new ReceiptTypeSammelCore(receiptTypeEntity);
    }
}
