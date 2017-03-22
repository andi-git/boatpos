package org.regkas.domain.core.repository;

import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.core.respository.MasterDataRepositoryCore;
import org.regkas.domain.core.builder.ProductGroupBuilderCore;
import org.regkas.domain.core.model.ProductGroupCore;
import org.regkas.model.ProductGroupEntity;
import org.regkas.domain.api.builder.ProductGroupBuilder;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.ProductGroup;
import org.regkas.domain.api.repository.ProductGroupRepository;
import org.regkas.domain.api.values.Name;

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
