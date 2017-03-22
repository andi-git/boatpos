package org.regkas.domain.api.context;

import org.regkas.domain.api.model.Company;

import java.util.Optional;

/**
 * Context for the {@link Company}.
 */
public interface CompanyContext {

    Company get();

    void set(Company company);

    void set(Optional<Company> company);

    void clear();
}
