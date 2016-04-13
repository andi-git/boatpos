package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.MasterDataWithDto;
import org.regkas.model.ProductGroupEntity;
import org.regkas.repository.api.values.Name;
import org.regkas.service.api.bean.ProductGroupBean;

import java.util.List;
import java.util.Set;

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
