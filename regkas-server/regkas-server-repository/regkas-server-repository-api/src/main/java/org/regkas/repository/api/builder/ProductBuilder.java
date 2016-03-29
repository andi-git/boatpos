package org.regkas.repository.api.builder;

import org.boatpos.common.repository.api.builder.MasterDataBuilder;
import org.boatpos.common.repository.api.builder.MasterDataBuilderWithDto;
import org.regkas.model.ProductEntity;
import org.regkas.model.ProductGroupEntity;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.values.Generic;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Price;
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
