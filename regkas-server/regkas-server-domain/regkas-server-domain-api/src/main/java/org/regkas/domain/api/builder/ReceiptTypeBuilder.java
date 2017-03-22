package org.regkas.domain.api.builder;

import org.boatpos.common.domain.api.builder.MasterDataBuilder;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.domain.api.model.ReceiptType;

/**
 * Builder for {@link ReceiptType}.
 */
public interface ReceiptTypeBuilder<MODEL extends ReceiptType, ENTITY extends ReceiptTypeEntity> extends MasterDataBuilder<ReceiptTypeBuilder, MODEL, ENTITY> {

    boolean canHandle(Class<ENTITY> receiptTypeEntityClass);

    ReceiptType build(ENTITY receiptTypeEntity);
}
