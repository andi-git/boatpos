package org.regkas.domain.core.builder;

import org.regkas.model.ReceiptTypeJahrEntity;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.ReceiptTypeJahr;
import org.regkas.domain.core.model.ReceiptTypeJahrCore;

import javax.enterprise.context.Dependent;

@Dependent
public class ReceiptTypeJahrBuilderCore extends ReceiptTypeBuilderCore<ReceiptTypeJahrBuilderCore, ReceiptTypeJahr, ReceiptTypeJahrCore, ReceiptTypeJahrEntity> {

    @Override
    public ReceiptType build() {
        return null;
    }

    @Override
    public ReceiptType build(ReceiptTypeJahrEntity receiptTypeEntity) {
        return new ReceiptTypeJahrCore(receiptTypeEntity);
    }
}
