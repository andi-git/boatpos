package org.regkas.service.core.context;

import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.repository.CompanyRepository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Optional;

/**
 * Context for the {@link Company}.
 */
@RequestScoped
public class CompanyContext extends DomainModelContext<Company> {

    @Inject
    private CompanyRepository companyRepository;

    @Override
    @Produces
    @Current
    @RequestScoped
    public Company get() {
        return getId().isPresent() ? companyRepository.loadBy(getId().get()).orElse(null) : null;
    }
}
