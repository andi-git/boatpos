package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.MasterDataWithDto;
import org.regkas.model.ProductGroupEntity;
import org.regkas.repository.api.values.Name;
import org.regkas.service.api.bean.ProductGroupBean;

/**
 * The domain model for a product-group.
 */
public interface ProductGroup extends MasterDataWithDto<ProductGroup, ProductGroupEntity, ProductGroupBean> {

    Name getName();

    ProductGroup setName(Name name);

    TaxSet getTaxSet();

    ProductGroup setTaxSet(TaxSet taxSet);
}
