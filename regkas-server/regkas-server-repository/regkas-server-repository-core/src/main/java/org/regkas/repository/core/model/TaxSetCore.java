package org.regkas.repository.core.model;

import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.repository.core.model.MasterDataCore;
import org.regkas.model.TaxSetEntity;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.TaxPercent;
import org.regkas.repository.core.mapping.TaxSetMapping;
import org.regkas.service.api.bean.TaxSetBean;

import static com.google.common.base.Preconditions.checkNotNull;

public class TaxSetCore extends MasterDataCore<TaxSet, TaxSetEntity> implements TaxSet {

    public TaxSetCore(DomainId id,
                      Version version,
                      Enabled enabled,
                      Priority priority,
                      KeyBinding keyBinding,
                      PictureUrl pictureUrl,
                      PictureUrlThumb pictureUrlThumb,
                      Name name,
                      TaxPercent taxPercent) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(name, "'name' must not be null");
        checkNotNull(taxPercent, "'taxPercent' must not be null");
        setName(name);
        setTaxPercent(taxPercent);
    }

    public TaxSetCore(TaxSetEntity taxSet) {
        super(taxSet);
    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    @Override
    public TaxSet setName(Name name) {
        getEntity().setName(SimpleValueObject.nullSafe(name));
        return this;
    }

    @Override
    public TaxPercent getTaxPercent() {
        return new TaxPercent(getEntity().getTaxPercent());
    }

    @Override
    public TaxSet setTaxPercent(TaxPercent taxPercent) {
        getEntity().setTaxPercent(SimpleValueObject.nullSafe(taxPercent));
        return this;
    }

    @Override
    public TaxSetBean asDto() {
        return TaxSetMapping.fromCDI().mapEntity(getEntity());
    }
}