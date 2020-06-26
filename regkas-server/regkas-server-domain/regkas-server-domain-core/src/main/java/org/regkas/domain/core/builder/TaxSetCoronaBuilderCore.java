package org.regkas.domain.core.builder;

import org.regkas.domain.api.model.TaxSet;
import org.regkas.domain.api.model.TaxSetCorona;
import org.regkas.domain.core.model.TaxSetCoronaCore;
import org.regkas.model.TaxSetCoronaEntity;

import javax.enterprise.context.Dependent;

@Dependent
public class TaxSetCoronaBuilderCore extends TaxSetBuilderCore<TaxSetCoronaBuilderCore, TaxSetCorona, TaxSetCoronaCore, TaxSetCoronaEntity> {

    @Override
    public TaxSet build() {
        return null;
    }

    @Override
    public TaxSet build(TaxSetCoronaEntity taxSetEntity) {
        return new TaxSetCoronaCore(taxSetEntity);
    }
}
