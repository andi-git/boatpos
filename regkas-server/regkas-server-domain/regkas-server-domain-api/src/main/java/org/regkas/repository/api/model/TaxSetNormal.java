package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.MasterDataWithDto;
import org.regkas.model.TaxSetEntity;
import org.regkas.model.TaxSetNormalEntity;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.TaxPercent;
import org.regkas.service.api.bean.TaxSetBean;

/**
 * The domain model for the tax-set: Normal.
 */
public interface TaxSetNormal extends TaxSet<TaxSetNormal, TaxSetNormalEntity> {

}
