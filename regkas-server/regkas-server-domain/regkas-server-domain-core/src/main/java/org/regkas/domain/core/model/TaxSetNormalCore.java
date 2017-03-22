package org.regkas.domain.core.model;

import org.regkas.model.TaxSetNormalEntity;
import org.regkas.domain.api.model.TaxSetNormal;

public class TaxSetNormalCore extends TaxSetCore<TaxSetNormal, TaxSetNormalEntity> implements TaxSetNormal {

    public TaxSetNormalCore(TaxSetNormalEntity taxSet) {
        super(taxSet);
    }
}