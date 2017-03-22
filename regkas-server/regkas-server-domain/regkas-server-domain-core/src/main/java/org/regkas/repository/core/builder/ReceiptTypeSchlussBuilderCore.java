package org.regkas.repository.core.builder;

import javax.enterprise.context.Dependent;

import org.regkas.model.ReceiptTypeSchlussEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.ReceiptTypeSchluss;
import org.regkas.repository.core.model.ReceiptTypeSchlussCore;

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
