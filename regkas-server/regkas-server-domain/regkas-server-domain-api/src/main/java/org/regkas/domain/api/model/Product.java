package org.regkas.domain.api.model;

import org.boatpos.common.domain.api.model.MasterDataWithDto;
import org.regkas.domain.api.values.Generic;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.Price;
import org.regkas.model.ProductEntity;
import org.regkas.service.api.bean.ProductBean;

/**
 * The domain model for a product.
 */
public interface Product extends MasterDataWithDto<Product, ProductEntity, ProductBean> {

    Name getName();

    Product setName(Name name);

    Price getPrice();

    Product setPrice(Price price);

    ProductGroup getProductGroup();

    Product setProductGroup(ProductGroup productGroup);

    Generic isGeneric();

    Product setGeneric(Generic generic);
}
