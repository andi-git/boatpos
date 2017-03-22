package org.regkas.repository.api.builder;

import org.boatpos.common.repository.api.builder.MasterDataBuilder;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.repository.api.model.ReceiptType;

/**
 * Builder for {@link ReceiptType}.
 */
public interface ReceiptTypeBuilder<MODEL extends ReceiptType, ENTITY extends ReceiptTypeEntity> extends MasterDataBuilder<ReceiptTypeBuilder, MODEL, ENTITY> {

    boolean canHandle(Class<ENTITY> receiptTypeEntityClass);

    ReceiptType build(ENTITY receiptTypeEntity);
}
