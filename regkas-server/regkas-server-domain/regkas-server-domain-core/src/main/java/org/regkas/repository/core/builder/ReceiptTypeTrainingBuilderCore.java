package org.regkas.repository.core.builder;

import org.regkas.model.ReceiptTypeTrainingEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.ReceiptTypeTraining;
import org.regkas.repository.core.model.ReceiptTypeTrainingCore;

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
