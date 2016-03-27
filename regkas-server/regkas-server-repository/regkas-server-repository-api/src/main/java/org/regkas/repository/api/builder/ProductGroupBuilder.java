package org.regkas.repository.api.builder;

import org.boatpos.common.repository.api.builder.MasterDataBuilder;
import org.boatpos.common.repository.api.builder.MasterDataBuilderWithDto;
import org.regkas.model.ProductGroupEntity;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.TaxPercent;
import org.regkas.service.api.bean.ProductGroupBean;

/**
 * Builder for {@link ProductGroup}.
 */
public interface ProductGroupBuilder extends MasterDataBuilderWithDto<ProductGroupBuilder, ProductGroup, ProductGroupEntity, ProductGroupBean> {

    ProductGroupBuilder add(Name name);

    ProductGroupBuilder add(TaxSet taxSet);
}
