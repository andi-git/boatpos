package org.regkas.domain.api.builder;

import org.boatpos.common.domain.api.builder.MasterDataBuilderWithDto;
import org.regkas.domain.api.model.ProductGroup;
import org.regkas.model.ProductEntity;
import org.regkas.domain.api.model.Product;
import org.regkas.domain.api.values.Generic;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.Price;
import org.regkas.service.api.bean.ProductBean;

/**
 * Builder for {@link Product}.
 */
public interface ProductBuilder extends MasterDataBuilderWithDto<ProductBuilder, Product, ProductEntity, ProductBean> {

    ProductBuilder add(Name name);

    ProductBuilder add(Price price);

    ProductBuilder add(ProductGroup productGroup);

    ProductBuilder add(Generic generic);
}
