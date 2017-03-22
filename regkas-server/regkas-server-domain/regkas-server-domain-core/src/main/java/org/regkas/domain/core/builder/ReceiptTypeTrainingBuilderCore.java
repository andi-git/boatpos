package org.regkas.domain.core.builder;

import org.regkas.model.ReceiptTypeTrainingEntity;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.ReceiptTypeTraining;
import org.regkas.domain.core.model.ReceiptTypeTrainingCore;

import javax.enterprise.context.Dependent;

@Dependent
public class ReceiptTypeTrainingBuilderCore extends ReceiptTypeBuilderCore<ReceiptTypeTrainingBuilderCore, ReceiptTypeTraining, ReceiptTypeTrainingCore, ReceiptTypeTrainingEntity> {

    @Override
    public ReceiptType build() {
        return null;
    }

    @Override
    public ReceiptType build(ReceiptTypeTrainingEntity receiptTypeEntity) {
        return new ReceiptTypeTrainingCore(receiptTypeEntity);
    }
}
