package org.regkas.repository.core.model;

import org.regkas.model.TaxSetErmaessigt2Entity;
import org.regkas.repository.api.model.TaxSetErmaessigt2;

public class TaxSetErmaessigt2Core extends TaxSetCore<TaxSetErmaessigt2, TaxSetErmaessigt2Entity> implements TaxSetErmaessigt2 {

    public TaxSetErmaessigt2Core(TaxSetErmaessigt2Entity taxSet) {
        super(taxSet);
    }
}