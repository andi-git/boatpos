package org.regkas.repository.core.builder;

import org.boatpos.common.repository.core.builder.MasterDataBuilderCore;
import org.boatpos.common.repository.core.builder.MasterDataBuilderCoreWithDto;
import org.regkas.model.ProductEntity;
import org.regkas.repository.api.builder.ProductBuilder;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Price;
import org.regkas.repository.core.model.ProductCore;
import org.regkas.service.api.bean.ProductBean;

import javax.enterprise.context.Dependent;

@Dependent
public class ProductBuilderCore
        extends MasterDataBuilderCoreWithDto<ProductBuilder, Product, ProductCore, ProductEntity, ProductBean>
        implements ProductBuilder {

    private Name name;

    private Price price;

    private ProductGroup productGroup;

    @Override
    public Product build() {
        return new ProductCore(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb, name, price, productGroup);
    }

    @Override
    public ProductBuilder add(Name name) {
        this.name = name;
        return this;
    }

    @Override
    public ProductBuilder add(Price price) {
        this.price = price;
        return this;
    }

    @Override
    public ProductBuilder add(ProductGroup productGroup) {
        this.productGroup = productGroup;
        return this;
    }
}
