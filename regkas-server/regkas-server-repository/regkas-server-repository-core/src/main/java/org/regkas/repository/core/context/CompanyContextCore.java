package org.regkas.repository.core.context;

import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.context.CompanyContext;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.repository.CompanyRepository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@RequestScoped
public class CompanyContextCore extends DomainModelContext<Company> implements CompanyContext {

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
