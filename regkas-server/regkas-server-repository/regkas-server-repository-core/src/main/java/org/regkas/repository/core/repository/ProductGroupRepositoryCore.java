package org.regkas.repository.core.repository;

import org.boatpos.common.repository.api.values.Enabled;
import org.boatpos.common.repository.core.respository.MasterDataRepositoryCore;
import org.regkas.model.ProductEntity;
import org.regkas.model.ProductGroupEntity;
import org.regkas.repository.api.builder.ProductBuilder;
import org.regkas.repository.api.builder.ProductGroupBuilder;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.repository.ProductGroupRepository;
import org.regkas.repository.api.repository.ProductRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.builder.ProductBuilderCore;
import org.regkas.repository.core.builder.ProductGroupBuilderCore;
import org.regkas.repository.core.model.ProductCore;
import org.regkas.repository.core.model.ProductGroupCore;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class ProductGroupRepositoryCore extends MasterDataRepositoryCore<ProductGroup, ProductGroupCore, ProductGroupEntity, ProductGroupBuilder, ProductGroupBuilderCore> implements ProductGroupRepository {

    @Override
    public Optional<ProductGroup> loadBy(Name name, Company company) {
        checkNotNull(name, "'name' must not be null");
        checkNotNull(company, "'company' must not be null");
        return loadByParameter(queryName("getByNameAndCompany"), (query) -> query.setParameter("name", name.get()).setParameter("company", company.getName().get()));
    }

    @Override
    public List<ProductGroup> loadBy(Company company) {
        return loadAll(queryName("getAllByCompany"), ProductGroupCore::new, (query) -> query.setParameter("companyId", company.getId().get()));
    }

    @Override
    public List<ProductGroup> loadBy(Company company, Enabled enabled) {
        checkNotNull(company, "'company' must not be null");
        checkNotNull(enabled, "'enabled' must not be null");
        return loadAll(queryName("getAll") + (enabled.get() ? "Enabled" : "Disabled") + "ByCompany", ProductGroupCore::new, (query) -> query.setParameter("companyId", company.getId().get()));
    }

    @Override
    protected String namedQueryPrefix() {
        return "productgroup";
    }
}
