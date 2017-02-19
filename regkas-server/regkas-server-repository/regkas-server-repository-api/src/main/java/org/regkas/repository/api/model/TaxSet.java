package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.MasterDataWithDto;
import org.regkas.model.CompanyEntity;
import org.regkas.model.TaxSetEntity;
import org.regkas.repository.api.values.*;
import org.regkas.service.api.bean.CompanyBean;
import org.regkas.service.api.bean.TaxSetBean;

import java.util.Set;

/**
 * The domain model for a tax-set.
 */
public interface TaxSet<MODEL extends TaxSet, ENTITY extends TaxSetEntity> extends MasterDataWithDto<MODEL, ENTITY, TaxSetBean> {

    Name getName();

    TaxPercent getTaxPercent();

    TotalPrice getTaxOf(TotalPrice totalPriceWithTax);

    TotalPrice getPriceWithoutTaxOf(TotalPrice totalPriceWithTax);
}
