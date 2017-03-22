package org.regkas.repository.core.builder;

import javax.enterprise.context.Dependent;

import org.regkas.model.ReceiptTypeJahrEntity;
import org.regkas.model.ReceiptTypeSammelEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.ReceiptTypeJahr;
import org.regkas.repository.api.model.ReceiptTypeSammel;
import org.regkas.repository.core.model.ReceiptTypeJahrCore;
import org.regkas.repository.core.model.ReceiptTypeSammelCore;

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
