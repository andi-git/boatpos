package org.regkas.domain.core.model;

import org.regkas.domain.api.model.TaxSetCorona;
import org.regkas.domain.api.values.SpecialTaxSet;
import org.regkas.model.TaxSetCoronaEntity;

public class TaxSetCoronaCore extends TaxSetCore<TaxSetCorona, TaxSetCoronaEntity> implements TaxSetCorona {

    public TaxSetCoronaCore(TaxSetCoronaEntity taxSet) {
        super(taxSet);
    }

    @Override
    public SpecialTaxSet isSpecialTaxSet() {
        return SpecialTaxSet.TRUE;
    }
}
