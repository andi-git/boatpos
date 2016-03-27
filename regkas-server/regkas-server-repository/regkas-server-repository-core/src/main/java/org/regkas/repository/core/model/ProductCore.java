package org.regkas.repository.core.model;

import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.repository.core.model.MasterDataCore;
import org.regkas.model.ProductEntity;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Price;
import org.regkas.repository.core.mapping.ProductMapping;
import org.regkas.service.api.bean.ProductBean;

import static com.google.common.base.Preconditions.checkNotNull;

public class ProductCore extends MasterDataCore<Product, ProductEntity> implements Product {

    public ProductCore(DomainId id,
                       Version version,
                       Enabled enabled,
                       Priority priority,
                       KeyBinding keyBinding,
                       PictureUrl pictureUrl,
                       PictureUrlThumb pictureUrlThumb,
                       Name name,
                       Price price,
                       ProductGroup productGroup) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(name, "'name' must not be null");
        checkNotNull(price, "'price' must not be null");
        checkNotNull(productGroup, "'productGroup' must not be null");
        setName(name);
        setPrice(price);
        setProductGroup(productGroup);
    }

    public ProductCore(ProductEntity product) {
        super(product);
    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    @Override
    public Product setName(Name name) {
        getEntity().setName(SimpleValueObject.nullSafe(name));
        return this;
    }

    @Override
    public Price getPrice() {
        return new Price(getEntity().getPrice());
    }

    @Override
    public Product setPrice(Price price) {
        getEntity().setPrice(SimpleValueObject.nullSafe(price));
        return this;
    }

    @Override
    public ProductGroup getProductGroup() {
        return new ProductGroupCore(getEntity().getProductGroup());
    }

    @Override
    public Product setProductGroup(ProductGroup productGroup) {
        if (productGroup != null) getEntity().setProductGroup(productGroup.asEntity());
        return this;
    }

    @Override
    public ProductBean asDto() {
        return ProductMapping.fromCDI().mapEntity(getEntity());
    }
}