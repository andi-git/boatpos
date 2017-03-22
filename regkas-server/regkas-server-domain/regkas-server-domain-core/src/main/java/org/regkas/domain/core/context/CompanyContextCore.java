package org.regkas.domain.core.context;

import org.boatpos.common.util.qualifiers.Current;
import org.regkas.domain.api.context.CompanyContext;
import org.regkas.domain.api.model.Company;
import org.regkas.domain.api.repository.CompanyRepository;

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
