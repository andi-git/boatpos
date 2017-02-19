package org.regkas.repository.core.builder;

import org.regkas.model.TaxSetNullEntity;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.model.TaxSetNull;
import org.regkas.repository.core.model.TaxSetNullCore;

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
