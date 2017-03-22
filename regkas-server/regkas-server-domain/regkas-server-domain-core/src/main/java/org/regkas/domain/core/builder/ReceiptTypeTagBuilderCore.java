package org.regkas.domain.core.builder;

import javax.enterprise.context.Dependent;

import org.regkas.domain.core.model.ReceiptTypeTagCore;
import org.regkas.model.ReceiptTypeTagEntity;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.ReceiptTypeTag;

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
