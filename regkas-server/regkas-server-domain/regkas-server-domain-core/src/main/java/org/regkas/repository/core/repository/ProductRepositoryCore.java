package org.regkas.repository.core.repository;

import org.boatpos.common.repository.api.values.Enabled;
import org.boatpos.common.repository.core.respository.MasterDataRepositoryCore;
import org.regkas.model.ProductEntity;
import org.regkas.repository.api.builder.ProductBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.repository.ProductRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.builder.ProductBuilderCore;
import org.regkas.repository.core.model.ProductCore;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class ProductRepositoryCore extends MasterDataRepositoryCore<Product, ProductCore, ProductEntity, ProductBuilder, ProductBuilderCore> implements ProductRepository {

    @Override
    public Optional<Product> loadGenericBy(ProductGroup productGroup) {
        checkNotNull(productGroup, "'name' must not be null");
        return loadByParameter(queryName("getGenericByProductGroup"),
                (query) -> query
                        .setParameter("productGroupName", productGroup.getName().get())
                        .setParameter("cashBoxName", productGroup.getCashBox().getName().get()));
    }

    @Override
    public Optional<Product> loadBy(Name name, CashBox cashBox) {
        checkNotNull(name, "'name' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        return loadByParameter(queryName("getByNameAndCashBox"),
                (query) -> query
                        .setParameter("name", name.get())
                        .setParameter("cashBox", cashBox.getName().get()));
    }

    @Override
    public List<Product> loadBy(CashBox cashBox) {
        checkNotNull(cashBox, "'cashBox' must not be null");
        return loadAll(queryName("getAllByCashBox"), ProductCore::new, (query) -> query.setParameter("cashBoxId", cashBox.getId().get()));
    }

    @Override
    public List<Product> loadBy(CashBox cashBox, Enabled enabled) {
        checkNotNull(cashBox, "'cashBox' must not be null");
        checkNotNull(enabled, "'enabled' must not be null");
        return loadAll(queryName("getAll") + (enabled.get() ? "Enabled" : "Disabled") + "ByCashBox", ProductCore::new, (query) -> query.setParameter("cashBoxId", cashBox.getId().get()));
    }

    @Override
    public List<Product> loadBy(ProductGroup productGroup) {
        checkNotNull(productGroup, "'productGroup' must not be null");
        return loadAll(queryName("getByProductGroup"), ProductCore::new, (query) -> query.setParameter("id", productGroup.getId().get()));
    }

    @Override
    protected String namedQueryPrefix() {
        return "product";
    }
}
