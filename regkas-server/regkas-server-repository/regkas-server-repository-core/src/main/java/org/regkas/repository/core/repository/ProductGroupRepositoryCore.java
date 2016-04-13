package org.regkas.repository.core.repository;

import org.boatpos.common.repository.api.values.Enabled;
import org.boatpos.common.repository.core.respository.MasterDataRepositoryCore;
import org.regkas.model.ProductGroupEntity;
import org.regkas.repository.api.builder.ProductGroupBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.repository.ProductGroupRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.builder.ProductGroupBuilderCore;
import org.regkas.repository.core.model.ProductGroupCore;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class ProductGroupRepositoryCore extends MasterDataRepositoryCore<ProductGroup, ProductGroupCore, ProductGroupEntity, ProductGroupBuilder, ProductGroupBuilderCore> implements ProductGroupRepository {

    @Override
    public Optional<ProductGroup> loadBy(Name name, CashBox cashBox) {
        checkNotNull(name, "'name' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        return loadByParameter(queryName("getByNameAndCashBox"), (query) -> query.setParameter("name", name.get()).setParameter("cashBox", cashBox.getName().get()));
    }

    @Override
    public List<ProductGroup> loadBy(CashBox cashBox) {
        return loadAll(queryName("getAllByCashBox"), ProductGroupCore::new, (query) -> query.setParameter("cashBoxId", cashBox.getId().get()));
    }

    @Override
    public List<ProductGroup> loadBy(CashBox cashBox, Enabled enabled) {
        checkNotNull(cashBox, "'cashBox' must not be null");
        checkNotNull(enabled, "'enabled' must not be null");
        return loadAll(queryName("getAll") + (enabled.get() ? "Enabled" : "Disabled") + "ByCashBox", ProductGroupCore::new, (query) -> query.setParameter("cashBoxId", cashBox.getId().get()));
    }

    @Override
    protected String namedQueryPrefix() {
        return "productgroup";
    }
}
