package org.regkas.domain.core.repository;

import org.boatpos.common.domain.core.respository.MasterDataRepositoryCore;
import org.regkas.domain.core.model.CompanyCore;
import org.regkas.model.CompanyEntity;
import org.regkas.domain.api.builder.CompanyBuilder;
import org.regkas.domain.api.model.Company;
import org.regkas.domain.api.model.User;
import org.regkas.domain.api.repository.CompanyRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.core.builder.CompanyBuilderCore;

import javax.enterprise.context.Dependent;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class CompanyRepositoryCore extends MasterDataRepositoryCore<Company, CompanyCore, CompanyEntity, CompanyBuilder, CompanyBuilderCore> implements CompanyRepository {

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
