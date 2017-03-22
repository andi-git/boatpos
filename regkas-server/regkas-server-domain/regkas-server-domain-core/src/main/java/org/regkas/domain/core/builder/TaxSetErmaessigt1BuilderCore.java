package org.regkas.domain.core.builder;

import org.regkas.domain.core.model.TaxSetErmaessigt1Core;
import org.regkas.model.TaxSetErmaessigt1Entity;
import org.regkas.domain.api.model.TaxSet;
import org.regkas.domain.api.model.TaxSetErmaessigt1;

import javax.enterprise.context.Dependent;

@Dependent
public class TaxSetErmaessigt1BuilderCore extends TaxSetBuilderCore<TaxSetErmaessigt1BuilderCore, TaxSetErmaessigt1, TaxSetErmaessigt1Core, TaxSetErmaessigt1Entity> {

    @Override
    public TaxSet build() {
        return null;
    }

    @Override
    public TaxSet build(TaxSetErmaessigt1Entity taxSetEntity) {
        return new TaxSetErmaessigt1Core(taxSetEntity);
    }
}
