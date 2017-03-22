package org.regkas.domain.core.model;

import org.regkas.model.TaxSetErmaessigt1Entity;
import org.regkas.domain.api.model.TaxSetErmaessigt1;

public class TaxSetErmaessigt1Core extends TaxSetCore<TaxSetErmaessigt1, TaxSetErmaessigt1Entity> implements TaxSetErmaessigt1 {

    public TaxSetErmaessigt1Core(TaxSetErmaessigt1Entity taxSet) {
        super(taxSet);
    }
}