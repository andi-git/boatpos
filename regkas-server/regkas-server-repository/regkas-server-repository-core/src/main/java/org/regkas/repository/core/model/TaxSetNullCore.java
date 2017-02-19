package org.regkas.repository.core.model;

import org.regkas.model.TaxSetNullEntity;
import org.regkas.repository.api.model.TaxSetNull;
import org.regkas.repository.api.values.TotalPrice;

public class TaxSetNullCore extends TaxSetCore<TaxSetNull, TaxSetNullEntity> implements TaxSetNull {

    public TaxSetNullCore(TaxSetNullEntity taxSet) {
        super(taxSet);
    }

    @Override
    public TotalPrice getPriceWithoutTaxOf(TotalPrice totalPriceWithTax) {
        return totalPriceWithTax;
    }

    @Override
    public TotalPrice getTaxOf(TotalPrice totalPriceWithTax) {
        return new TotalPrice("0.00");
    }
}