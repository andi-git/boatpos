package org.regkas.repository.api.builder;

import org.boatpos.common.repository.api.builder.MasterDataBuilder;
import org.boatpos.common.repository.api.builder.MasterDataBuilderWithDto;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.model.TaxSetEntity;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.TaxPercent;

/**
 * Builder for {@link ReceiptType}.
 */
public interface ReceiptTypeBuilder extends MasterDataBuilder<ReceiptTypeBuilder, ReceiptType, ReceiptTypeEntity> {

    ReceiptTypeBuilder add(Name name);
}
