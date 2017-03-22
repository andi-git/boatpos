package org.regkas.domain.core.builder;

import org.regkas.model.TaxSetNormalEntity;
import org.regkas.domain.api.model.TaxSet;
import org.regkas.domain.api.model.TaxSetNormal;
import org.regkas.domain.core.model.TaxSetNormalCore;

import javax.enterprise.context.Dependent;

@Dependent
public class TaxSetNormalBuilderCore extends TaxSetBuilderCore<TaxSetNormalBuilderCore, TaxSetNormal, TaxSetNormalCore, TaxSetNormalEntity> {

    @Override
    public TaxSet build() {
        return null;
    }

    @Override
    public TaxSet build(TaxSetNormalEntity taxSetEntity) {
        return new TaxSetNormalCore(taxSetEntity);
    }
}
