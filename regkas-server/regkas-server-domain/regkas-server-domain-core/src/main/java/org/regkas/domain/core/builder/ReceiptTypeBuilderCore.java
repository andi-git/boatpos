package org.regkas.domain.core.builder;

import org.boatpos.common.domain.core.builder.MasterDataBuilderCore;
import org.regkas.domain.core.model.ReceiptTypeCore;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.domain.api.builder.ReceiptTypeBuilder;
import org.regkas.domain.api.model.ReceiptType;

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
