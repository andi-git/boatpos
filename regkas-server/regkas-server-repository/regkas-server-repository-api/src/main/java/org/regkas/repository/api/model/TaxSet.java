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
public interface TaxSet extends MasterDataWithDto<TaxSet, TaxSetEntity, TaxSetBean> {

    Name getName();

    TaxSet setName(Name name);

    TaxPercent getTaxPercent();

    TaxSet setTaxPercent(TaxPercent taxPercent);
}
