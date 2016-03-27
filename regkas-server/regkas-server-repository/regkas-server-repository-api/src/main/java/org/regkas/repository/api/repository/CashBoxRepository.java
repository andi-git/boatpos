package org.regkas.repository.api.repository;

import org.boatpos.common.repository.api.repository.MasterDataRepository;
import org.regkas.repository.api.builder.CashBoxBuilder;
import org.regkas.repository.api.builder.UserBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.values.Name;

import java.util.Optional;

/**
 * The repository for the {@link CashBox}.
 */
public interface CashBoxRepository extends MasterDataRepository<CashBox, CashBoxBuilder> {

    /**
     * Load all attributes based on the {@link Name}.
     *
     * @param name the {@link Name} of the {@link CashBox}
     * @return the current {@link CashBox} with all attributes from the repository
     */
    Optional<CashBox> loadBy(Name name);
}
