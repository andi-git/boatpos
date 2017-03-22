package org.regkas.domain.core.builder;

import org.regkas.domain.core.model.TaxSetNullCore;
import org.regkas.model.TaxSetNullEntity;
import org.regkas.domain.api.model.TaxSet;
import org.regkas.domain.api.model.TaxSetNull;

import javax.enterprise.context.Dependent;

@Dependent
public class TaxSetNullBuilderCore extends TaxSetBuilderCore<TaxSetNullBuilderCore, TaxSetNull, TaxSetNullCore, TaxSetNullEntity> {

    @Override
    public TaxSet build() {
        return null;
    }

    @Override
    public TaxSet build(TaxSetNullEntity taxSetEntity) {
        return new TaxSetNullCore(taxSetEntity);
    }
}
