package org.regkas.repository.core.builder;

import org.boatpos.common.repository.core.builder.MasterDataBuilderCore;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.repository.api.builder.ReceiptTypeBuilder;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.model.ReceiptTypeCore;

import javax.enterprise.context.Dependent;

@Dependent
public class ReceiptTypeBuilderCore
        extends MasterDataBuilderCore<ReceiptTypeBuilder, ReceiptType, ReceiptTypeCore, ReceiptTypeEntity>
        implements ReceiptTypeBuilder {

    private Name name;

    @Override
    public ReceiptType build() {
        return new ReceiptTypeCore(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb, name);
    }

    @Override
    public ReceiptTypeBuilder add(Name name) {
        this.name = name;
        return this;
    }
}
