package org.regkas.repository.core.builder;

import org.regkas.model.TaxSetErmaessigt2Entity;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.model.TaxSetErmaessigt2;
import org.regkas.repository.core.model.TaxSetErmaessigt2Core;

import javax.enterprise.context.Dependent;

@Dependent
public class TaxSetErmaessigt2BuilderCore extends TaxSetBuilderCore<TaxSetErmaessigt2BuilderCore, TaxSetErmaessigt2, TaxSetErmaessigt2Core, TaxSetErmaessigt2Entity> {

    @Override
    public TaxSet build() {
        return null;
    }

    @Override
    public TaxSet build(TaxSetErmaessigt2Entity taxSetEntity) {
        return new TaxSetErmaessigt2Core(taxSetEntity);
    }
}
