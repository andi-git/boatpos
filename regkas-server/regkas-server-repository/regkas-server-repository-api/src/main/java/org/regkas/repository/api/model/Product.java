package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.MasterDataWithDto;
import org.regkas.model.ProductEntity;
import org.regkas.model.ProductGroupEntity;
import org.regkas.repository.api.values.Generic;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Price;
import org.regkas.service.api.bean.ProductBean;
import org.regkas.service.api.bean.ProductGroupBean;

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
