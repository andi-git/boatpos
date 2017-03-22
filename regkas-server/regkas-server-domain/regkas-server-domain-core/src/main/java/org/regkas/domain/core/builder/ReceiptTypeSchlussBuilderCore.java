package org.regkas.domain.core.builder;

import javax.enterprise.context.Dependent;

import org.regkas.model.ReceiptTypeSchlussEntity;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.ReceiptTypeSchluss;
import org.regkas.domain.core.model.ReceiptTypeSchlussCore;

@Dependent
public class ReceiptTypeSchlussBuilderCore
        extends
            ReceiptTypeBuilderCore<ReceiptTypeSchlussBuilderCore, ReceiptTypeSchluss, ReceiptTypeSchlussCore, ReceiptTypeSchlussEntity> {

    @Override
    public ReceiptType build() {
        return null;
    }

    @Override
    public ReceiptType build(ReceiptTypeSchlussEntity receiptTypeEntity) {
        return new ReceiptTypeSchlussCore(receiptTypeEntity);
    }
}
