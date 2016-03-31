package org.regkas.service.core.context;

import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.Company;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import java.util.Optional;

/**
 * Context for the {@link Company}.
 */
@RequestScoped
public class CompanyContext {

    private Company company;

    public void set(Company company) {
        this.company = company;
    }

    public void set(Optional<Company> company) {
        if (company.isPresent()) this.company= company.get();
    }

    public void clear() {
        this.company = null;
    }

    @Produces
    @Current
    @RequestScoped
    public Company get() {
        return this.company;
    }
}
