package org.regkas.repository.core.builder;

import org.regkas.model.TaxSetNormalEntity;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.model.TaxSetNormal;
import org.regkas.repository.core.model.TaxSetNormalCore;

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
