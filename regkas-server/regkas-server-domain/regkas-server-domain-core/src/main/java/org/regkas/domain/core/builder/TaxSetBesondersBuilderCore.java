package org.regkas.domain.core.builder;

import org.regkas.domain.core.model.TaxSetBesondersCore;
import org.regkas.model.TaxSetBesondersEntity;
import org.regkas.domain.api.model.TaxSet;
import org.regkas.domain.api.model.TaxSetBesonders;

import javax.enterprise.context.Dependent;

@Dependent
public class TaxSetBesondersBuilderCore extends TaxSetBuilderCore<TaxSetBesondersBuilderCore, TaxSetBesonders, TaxSetBesondersCore, TaxSetBesondersEntity> {

    @Override
    public TaxSet build() {
        return null;
    }

    @Override
    public TaxSet build(TaxSetBesondersEntity taxSetEntity) {
        return new TaxSetBesondersCore(taxSetEntity);
    }
}
