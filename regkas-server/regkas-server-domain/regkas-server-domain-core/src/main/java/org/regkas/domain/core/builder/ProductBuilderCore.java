package org.regkas.domain.core.builder;

import org.boatpos.common.domain.core.builder.MasterDataBuilderCoreWithDto;
import org.regkas.model.ProductEntity;
import org.regkas.domain.api.builder.ProductBuilder;
import org.regkas.domain.api.model.Product;
import org.regkas.domain.api.model.ProductGroup;
import org.regkas.domain.api.values.Generic;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.Price;
import org.regkas.domain.core.model.ProductCore;
import org.regkas.service.api.bean.ProductBean;

import javax.enterprise.context.Dependent;

@Dependent
public class ProductBuilderCore
        extends MasterDataBuilderCoreWithDto<ProductBuilder, Product, ProductCore, ProductEntity, ProductBean>
        implements ProductBuilder {

    private Name name;

    private Price price;

    private ProductGroup productGroup;

    private Generic generic;

    @Override
    public Product build() {
        return new ProductCore(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb, name, price, productGroup, generic);
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

    @Override
    public ProductBuilder add(Generic generic) {
        this.generic = generic;
        return this;
    }
}
