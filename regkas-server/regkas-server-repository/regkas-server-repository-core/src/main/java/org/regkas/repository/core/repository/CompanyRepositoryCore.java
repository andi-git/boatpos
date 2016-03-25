package org.regkas.repository.core.repository;

import org.boatpos.common.repository.core.respository.MasterDataRepositoryCore;
import org.regkas.model.CompanyEntity;
import org.regkas.repository.api.builder.CompanyBuilder;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.builder.CompanyBuilderCore;
import org.regkas.repository.core.model.CompanyCore;

import javax.enterprise.context.Dependent;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class CompanyRepositoryCore extends MasterDataRepositoryCore<Company, CompanyCore, CompanyEntity> implements CompanyRepository {

    @Override
    public CompanyBuilder builder() {
        return new CompanyBuilderCore();
    }

    @Override
    public Optional<Company> loadBy(Name name) {
        checkNotNull(name, "'name' must not be null");
        return loadByParameter("company.getByName", (query) -> query.setParameter("name", name.get()));
    }

    @Override
    public Optional<Company> loadBy(User user) {
        checkNotNull(user, "'user' must not be null");
        return loadByParameter("company.getByUser", (query) -> query.setParameter("name", user.getName().get()));
    }

    @Override
    protected String namedQueryPrefix() {
        return "company";
    }
}
