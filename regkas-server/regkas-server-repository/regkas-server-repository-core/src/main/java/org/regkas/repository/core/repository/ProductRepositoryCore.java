package org.regkas.repository.core.repository;

import org.boatpos.common.repository.api.values.Enabled;
import org.boatpos.common.repository.core.respository.MasterDataRepositoryCore;
import org.regkas.model.ProductEntity;
import org.regkas.repository.api.builder.ProductBuilder;
import org.regkas.repository.api.model.Company;
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
    public Optional<Product> loadBy(Name name) {
        checkNotNull(name, "'name' must not be null");
        return loadByParameter(queryName("getByName"), (query) -> query.setParameter("name", name.get()));
    }

    @Override
    public Optional<Product> loadGenericBy(ProductGroup productGroup) {
        checkNotNull(productGroup, "'name' must not be null");
        return loadByParameter(queryName("getGenericByProductGroup"), (query) -> query.setParameter("productGroupName", productGroup.getName().get()));
    }

    @Override
    public Optional<Product> loadBy(Name name, Company company) {
        checkNotNull(name, "'name' must not be null");
        checkNotNull(company, "'company' must not be null");
        return loadByParameter(queryName("getByNameAndCompany"), (query) -> query.setParameter("name", name.get()).setParameter("company", company.getName().get()));
    }

    @Override
    public List<Product> loadBy(Company company) {
        checkNotNull(company, "'company' must not be null");
        return loadAll(queryName("getAllByCompany"), ProductCore::new, (query) -> query.setParameter("companyId", company.getId().get()));
    }

    @Override
    public List<Product> loadBy(Company company, Enabled enabled) {
        checkNotNull(company, "'company' must not be null");
        checkNotNull(enabled, "'enabled' must not be null");
        return loadAll(queryName("getAll") + (enabled.get() ? "Enabled" : "Disabled") + "ByCompany", ProductCore::new, (query) -> query.setParameter("companyId", company.getId().get()));
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
