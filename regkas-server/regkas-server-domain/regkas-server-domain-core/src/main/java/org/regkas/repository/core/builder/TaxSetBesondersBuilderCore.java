package org.regkas.repository.core.builder;

import org.regkas.model.TaxSetBesondersEntity;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.model.TaxSetBesonders;
import org.regkas.repository.core.model.TaxSetBesondersCore;

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
