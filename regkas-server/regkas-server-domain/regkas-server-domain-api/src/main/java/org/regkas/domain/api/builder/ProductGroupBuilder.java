package org.regkas.domain.api.builder;

import org.boatpos.common.domain.api.builder.MasterDataBuilderWithDto;
import org.regkas.domain.api.model.ProductGroup;
import org.regkas.domain.api.model.TaxSet;
import org.regkas.domain.api.values.Name;
import org.regkas.model.ProductGroupEntity;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Product;
import org.regkas.service.api.bean.ProductGroupBean;

/**
 * Builder for {@link ProductGroup}.
 */
public interface ProductGroupBuilder extends MasterDataBuilderWithDto<ProductGroupBuilder, ProductGroup, ProductGroupEntity, ProductGroupBean> {

    ProductGroupBuilder add(Name name);

    ProductGroupBuilder add(TaxSet taxSet);

    ProductGroupBuilder add(CashBox cashBox);

    ProductGroupBuilder add(Product product);
}
