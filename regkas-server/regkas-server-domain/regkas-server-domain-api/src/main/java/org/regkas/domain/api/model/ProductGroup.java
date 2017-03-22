package org.regkas.domain.api.model;

import org.boatpos.common.domain.api.model.MasterDataWithDto;
import org.regkas.model.ProductGroupEntity;
import org.regkas.domain.api.values.Name;
import org.regkas.service.api.bean.ProductGroupBean;

import java.util.List;

/**
 * The domain model for a product-group.
 */
public interface ProductGroup extends MasterDataWithDto<ProductGroup, ProductGroupEntity, ProductGroupBean> {

    Name getName();

    ProductGroup setName(Name name);

    TaxSet getTaxSet();

    ProductGroup setTaxSet(TaxSet taxSet);

    CashBox getCashBox();

    ProductGroup setCashBox(CashBox cashBox);

    List<Product> getProducts();

    ProductGroup addProducts(List<Product> products);

    ProductGroup addProduct(Product product);

    ProductGroup clearProducts();
}
