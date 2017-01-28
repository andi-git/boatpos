package org.regkas.repository.core.builder;

import org.regkas.model.ReceiptTypeJahrEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.ReceiptTypeJahr;
import org.regkas.repository.api.model.ReceiptTypeStandard;
import org.regkas.repository.core.model.ReceiptTypeJahrCore;

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
