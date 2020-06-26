package org.regkas.domain.api.model;

import org.boatpos.common.domain.api.model.MasterDataWithDto;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.SpecialTaxSet;
import org.regkas.domain.api.values.TaxPercent;
import org.regkas.domain.api.values.TotalPrice;
import org.regkas.model.TaxSetEntity;
import org.regkas.service.api.bean.TaxSetBean;

/**
 * The domain model for a tax-set.
 */
public interface TaxSet<MODEL extends TaxSet, ENTITY extends TaxSetEntity> extends MasterDataWithDto<MODEL, ENTITY, TaxSetBean> {

    Name getName();

    TaxPercent getTaxPercent();

    TotalPrice getTaxOf(TotalPrice totalPriceWithTax);

    TotalPrice getPriceWithoutTaxOf(TotalPrice totalPriceWithTax);

    SpecialTaxSet isSpecialTaxSet();
}
