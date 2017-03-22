package org.regkas.domain.core.model;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.common.domain.core.model.MasterDataCore;
import org.regkas.domain.core.mapping.TaxSetMapping;
import org.regkas.model.TaxSetEntity;
import org.regkas.domain.api.model.TaxSet;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.TaxPercent;
import org.regkas.domain.api.values.TotalPrice;
import org.regkas.service.api.bean.TaxSetBean;

import java.math.BigDecimal;

public abstract class TaxSetCore<MODEL extends TaxSet, ENTITY extends TaxSetEntity> extends MasterDataCore<MODEL, ENTITY> implements TaxSet<MODEL, ENTITY> {

    public TaxSetCore(ENTITY taxSet) {
        super(taxSet);
    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    protected TaxSet setName(Name name) {
        getEntity().setName(SimpleValueObject.nullSafe(name));
        return this;
    }

    @Override
    public TaxPercent getTaxPercent() {
        return new TaxPercent(getEntity().getTaxPercent());
    }

    protected TaxSet setTaxPercent(TaxPercent taxPercent) {
        getEntity().setTaxPercent(SimpleValueObject.nullSafe(taxPercent));
        return this;
    }

    @Override
    public TotalPrice getPriceWithoutTaxOf(TotalPrice totalPriceWithTax) {
        BigDecimal divisor = new BigDecimal("1.00").add(new BigDecimal(getTaxPercent().get() + ".00").divide(new BigDecimal("100.00"), 2, BigDecimal.ROUND_HALF_UP));
        return new TotalPrice(totalPriceWithTax.get().divide(divisor, 2, BigDecimal.ROUND_HALF_UP));
    }

    @Override
    public TotalPrice getTaxOf(TotalPrice totalPriceWithTax) {
        return new TotalPrice(totalPriceWithTax.get().subtract(getPriceWithoutTaxOf(totalPriceWithTax).get()));
    }

    @Override
    public TaxSetBean asDto() {
        return TaxSetMapping.fromCDI().mapEntity(getEntity());
    }
}