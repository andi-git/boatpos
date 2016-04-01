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
public class CompanyContext {

    @Inject
    private CompanyRepository companyRepository;

    private DomainId id;

    public void set(Company company) {
        if (company != null) {
            this.id = company.getId();
        }
    }

    public void set(Optional<Company> company) {
        if (company.isPresent()) set(company.get());
    }

    public void clear() {
        this.id = null;
    }

    @Produces
    @Current
    @RequestScoped
    public Company get() {
        return id == null ? null : companyRepository.loadBy(id).orElse(null);
    }
}
