package org.regkas.domain.core.model;

import org.boatpos.common.domain.api.model.DomainModel;
import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.common.domain.api.values.Version;
import org.boatpos.common.domain.core.model.MasterDataCore;
import org.regkas.model.ProductGroupEntity;
import org.regkas.model.TaxSetEntity;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Product;
import org.regkas.domain.api.model.ProductGroup;
import org.regkas.domain.api.model.TaxSet;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.core.builder.TaxSetBuilderHolder;
import org.regkas.domain.core.mapping.ProductGroupMapping;
import org.regkas.service.api.bean.ProductGroupBean;

import javax.enterprise.inject.spi.CDI;
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
                            CashBox cashBox,
                            List<Product> products) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(name, "'name' must not be null");
        checkNotNull(taxSet, "'taxSet' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        checkNotNull(products, "'products' must not be null");
        setName(name);
        setTaxSet(taxSet);
        setCashBox(cashBox);
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
        return CDI.current().select(TaxSetBuilderHolder.class).get()
                .getTaxSetFor(getEntity().getTaxSet())
                .orElseThrow(() -> new RuntimeException("no builder available for " + getEntity().getTaxSet().getClass().getName()));
    }

    @Override
    public ProductGroup setTaxSet(TaxSet taxSet) {
        if (taxSet != null) {
            TaxSetEntity entity = (TaxSetEntity) taxSet.asEntity();
            getEntity().setTaxSet(entity);
        }
        return this;
    }

    @Override
    public CashBox getCashBox() {
        return new CashBoxCore(getEntity().getCashBox());
    }

    @Override
    public ProductGroup setCashBox(CashBox cashBox) {
        if (cashBox != null) getEntity().setCashBox(cashBox.asEntity());
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