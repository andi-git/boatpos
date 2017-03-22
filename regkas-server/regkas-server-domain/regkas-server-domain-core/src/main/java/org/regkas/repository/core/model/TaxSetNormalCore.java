package org.regkas.repository.core.model;

import org.regkas.model.TaxSetNormalEntity;
import org.regkas.repository.api.model.TaxSetNormal;

public class TaxSetNormalCore extends TaxSetCore<TaxSetNormal, TaxSetNormalEntity> implements TaxSetNormal {

    public TaxSetNormalCore(TaxSetNormalEntity taxSet) {
        super(taxSet);
    }
}