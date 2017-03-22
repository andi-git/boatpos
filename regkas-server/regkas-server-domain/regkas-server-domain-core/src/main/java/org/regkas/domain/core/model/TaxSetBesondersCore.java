package org.regkas.domain.core.model;

import org.regkas.model.TaxSetBesondersEntity;
import org.regkas.domain.api.model.TaxSetBesonders;

public class TaxSetBesondersCore extends TaxSetCore<TaxSetBesonders, TaxSetBesondersEntity> implements TaxSetBesonders {

    public TaxSetBesondersCore(TaxSetBesondersEntity taxSet) {
        super(taxSet);
    }
}