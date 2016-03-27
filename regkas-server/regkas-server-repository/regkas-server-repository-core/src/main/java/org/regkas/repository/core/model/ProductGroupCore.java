package org.regkas.repository.core.model;

import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.repository.core.model.MasterDataCore;
import org.regkas.model.ProductGroupEntity;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.mapping.ProductGroupMapping;
import org.regkas.service.api.bean.ProductGroupBean;

import static com.google.common.base.Preconditions.checkNotNull;

public class ProductGroupCore extends MasterDataCore<ProductGroup, ProductGroupEntity> implements ProductGroup {

    public ProductGroupCore(DomainId id,
                            Version version,
                            Enabled enabled,
                            Priority priority,
                            KeyBinding keyBinding,
                            PictureUrl pictureUrl,
                            PictureUrlThumb pictureUrlThumb,
                            Name name,
                            TaxSet taxSet) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(name, "'name' must not be null");
        checkNotNull(taxSet, "'taxSet' must not be null");
        setName(name);
        setTaxSet(taxSet);
    }

    public ProductGroupCore(ProductGroupEntity productGroup) {
        super(productGroup);
    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    @Override
    public ProductGroup setName(Name name) {
        getEntity().setName(SimpleValueObject.nullSafe(name));
        return this;
    }

    @Override
    public TaxSet getTaxSet() {
        return new TaxSetCore(getEntity().getTaxSet());
    }

    @Override
    public ProductGroup setTaxSet(TaxSet taxSet) {
        if (taxSet != null) getEntity().setTaxSet(taxSet.asEntity());
        return this;
    }

    @Override
    public ProductGroupBean asDto() {
        return ProductGroupMapping.fromCDI().mapEntity(getEntity());
    }
}