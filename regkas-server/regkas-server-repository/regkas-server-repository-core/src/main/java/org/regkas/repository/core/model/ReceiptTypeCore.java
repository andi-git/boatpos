package org.regkas.repository.core.model;

import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.repository.core.model.MasterDataCore;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.values.Name;

import static com.google.common.base.Preconditions.checkNotNull;

public class ReceiptTypeCore extends MasterDataCore<ReceiptType, ReceiptTypeEntity> implements ReceiptType {

    public ReceiptTypeCore(DomainId id,
                           Version version,
                           Enabled enabled,
                           Priority priority,
                           KeyBinding keyBinding,
                           PictureUrl pictureUrl,
                           PictureUrlThumb pictureUrlThumb,
                           Name name) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(name, "'name' must not be null");
        setName(name);
    }

    public ReceiptTypeCore(ReceiptTypeEntity receiptType) {
        super(receiptType);
    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    @Override
    public ReceiptType setName(Name name) {
        getEntity().setName(SimpleValueObject.nullSafe(name));
        return this;
    }
}