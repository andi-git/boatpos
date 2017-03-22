package org.regkas.domain.api.repository;

import org.boatpos.common.domain.api.repository.MasterDataRepository;
import org.regkas.domain.api.builder.CashBoxBuilder;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.values.Name;

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
