package org.regkas.repository.core.model;

import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.repository.core.model.MasterDataCore;
import org.regkas.model.ProductGroupEntity;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.mapping.ProductGroupMapping;
import org.regkas.service.api.bean.ProductGroupBean;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class ProductGroupCore extends MasterDataCore<ProductGroup, ProductGroupEntity> implements ProductGroup {

    public ProductGroupCore(DomainId id,
                            Version version,
                            Enabled enabled,
                            Priority priority,
                            KeyBinding keyBinding,
                            PictureUrl pictureUrl,
                            PictureUrlThumb pictureUrlThumb,
                            Name name,
                            TaxSet taxSet,
                            List<Product> products)  {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(name, "'name' must not be null");
        checkNotNull(taxSet, "'taxSet' must not be null");
        checkNotNull(products, "'products' must not be null");
        setName(name);
        setTaxSet(taxSet);
        addProducts(products);
    }

    public ProductGroupCore(ProductGroupEntity productGroup) {
        super(productGroup);
    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    @Override
    public ProductGroup setName(Name name) {
        getEntity().setName(SimpleValueObject.nullSafe(name));
        return this;
    }

    @Override
    public TaxSet getTaxSet() {
        return new TaxSetCore(getEntity().getTaxSet());
    }

    @Override
    public ProductGroup setTaxSet(TaxSet taxSet) {
        if (taxSet != null) getEntity().setTaxSet(taxSet.asEntity());
        return this;
    }

    @Override
    public List<Product> getProducts() {
        return Collections.unmodifiableList(
                getEntity().getProducts().stream()
                        .map(ProductCore::new)
                        .sorted((p1, p2) -> p1.getPriority().compareTo(p2.getPriority()))
                        .collect(Collectors.toList()));
    }

    @Override
    public ProductGroup addProducts(List<Product> products) {
        if (products != null) {
            getEntity().setProducts(products.stream().map(DomainModel::asEntity).collect(Collectors.toSet()));
        }
        return this;
    }

    @Override
    public ProductGroup addProduct(Product product) {
        if (product != null) {
            getEntity().getProducts().add(product.asEntity());
        }
        return this;
    }

    @Override
    public ProductGroup clearProducts() {
        getEntity().getProducts().clear();
        return this;
    }

    @Override
    public ProductGroupBean asDto() {
        return ProductGroupMapping.fromCDI().mapEntity(getEntity());
    }
}