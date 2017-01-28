package org.regkas.repository.core.builder;

import org.boatpos.common.repository.core.builder.MasterDataBuilderCore;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.repository.api.builder.ReceiptTypeBuilder;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.core.model.ReceiptTypeCore;

// BUILDER and MODEL are not used, but are required in the superclass when getting the classes dynamically
@SuppressWarnings("unused")
public abstract class ReceiptTypeBuilderCore<BUILDER extends ReceiptTypeBuilder, MODEL extends ReceiptType, MODELCORE extends ReceiptTypeCore, ENTITY extends ReceiptTypeEntity>
        extends MasterDataBuilderCore<ReceiptTypeBuilder, ReceiptType, MODELCORE, ENTITY>
        implements ReceiptTypeBuilder<ReceiptType, ENTITY> {

    @Override
    public ReceiptType build() {
        return null;
    }

    @Override
    public boolean canHandle(Class<ENTITY> receiptTypeEntityClass) {
        return getEntityClass() == receiptTypeEntityClass;
    }
}
