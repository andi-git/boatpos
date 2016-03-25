package org.regkas.repository.api.repository;

import org.boatpos.common.repository.api.repository.MasterDataRepositoryWithDto;
import org.regkas.repository.api.builder.CompanyBuilder;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.values.Name;

import java.util.Optional;

/**
 * The repository for the {@link Company}.
 */
public interface CompanyRepository extends MasterDataRepositoryWithDto<Company> {

    /**
     * Get the {@link CompanyBuilder}.
     *
     * @return the {@link CompanyBuilder}
     */
    CompanyBuilder builder();

    /**
     * Load all attributes based on the {@link Name}.
     *
     * @param name the {@link Name} of the {@link CompanyBuilder}
     * @return the current {@link CompanyBuilder} with all attributes from the repository
     */
    Optional<Company> loadBy(Name name);

    /**
     * Load all {@link Company} for a {@link User}.
     *
     * @param user the {@link User} of the {@link Company}
     * @return the current {@link Company} with all attributes from the repository
     */
    Optional<Company> loadBy(User user);
}
