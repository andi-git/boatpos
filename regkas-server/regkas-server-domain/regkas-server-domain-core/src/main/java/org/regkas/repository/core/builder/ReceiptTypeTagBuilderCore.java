package org.regkas.repository.core.builder;

import javax.enterprise.context.Dependent;

import org.regkas.model.ReceiptTypeTagEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.ReceiptTypeTag;
import org.regkas.repository.core.model.ReceiptTypeTagCore;

@Dependent
public class ReceiptTypeTagBuilderCore
        extends
            ReceiptTypeBuilderCore<ReceiptTypeTagBuilderCore, ReceiptTypeTag, ReceiptTypeTagCore, ReceiptTypeTagEntity> {

    @Override
    public ReceiptType build() {
        return null;
    }

    @Override
    public ReceiptType build(ReceiptTypeTagEntity receiptTypeEntity) {
        return new ReceiptTypeTagCore(receiptTypeEntity);
    }
}
