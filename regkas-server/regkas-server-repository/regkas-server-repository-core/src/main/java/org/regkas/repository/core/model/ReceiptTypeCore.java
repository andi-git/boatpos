package org.regkas.repository.core.model;

import org.boatpos.common.repository.core.model.MasterDataCore;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.mapping.ReceiptMapping;

import javax.enterprise.inject.spi.CDI;

public abstract class ReceiptTypeCore<MODEL extends ReceiptType, ENTITY extends ReceiptTypeEntity> extends MasterDataCore<MODEL, ENTITY> implements ReceiptType<MODEL, ENTITY> {

    public ReceiptTypeCore(ENTITY receiptType) {
        super(receiptType);
    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    protected <T> T fromCDI(Class<T> type) {
        return CDI.current().select(type).get();
    }
}