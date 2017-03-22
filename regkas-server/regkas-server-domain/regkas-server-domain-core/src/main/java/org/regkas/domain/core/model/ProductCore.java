package org.regkas.domain.core.model;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.common.domain.api.values.Version;
import org.boatpos.common.domain.core.model.MasterDataCore;
import org.regkas.model.ProductEntity;
import org.regkas.domain.api.model.Product;
import org.regkas.domain.api.model.ProductGroup;
import org.regkas.domain.api.values.Generic;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.Price;
import org.regkas.domain.core.mapping.ProductMapping;
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
                       ProductGroup productGroup,
                       Generic generic) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(name, "'name' must not be null");
        checkNotNull(price, "'price' must not be null");
        checkNotNull(productGroup, "'productGroup' must not be null");
        checkNotNull(generic, "'generic' must not be null");
        setName(name);
        setPrice(price);
        setProductGroup(productGroup);
        setGeneric(generic);
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
    public Generic isGeneric() {
        return new Generic(getEntity().getGeneric());
    }

    @Override
    public Product setGeneric(Generic generic) {
        getEntity().setGeneric(SimpleValueObject.nullSafe(generic));
        //noinspection unchecked
        return this;
    }

    @Override
    public ProductBean asDto() {
        return ProductMapping.fromCDI().mapEntity(getEntity());
    }
}