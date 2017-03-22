package org.regkas.repository.core.model;

import org.regkas.model.TaxSetBesondersEntity;
import org.regkas.repository.api.model.TaxSetBesonders;

public class TaxSetBesondersCore extends TaxSetCore<TaxSetBesonders, TaxSetBesondersEntity> implements TaxSetBesonders {

    public TaxSetBesondersCore(TaxSetBesondersEntity taxSet) {
        super(taxSet);
    }
}