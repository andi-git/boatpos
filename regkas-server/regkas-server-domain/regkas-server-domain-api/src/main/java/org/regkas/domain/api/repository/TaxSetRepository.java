package org.regkas.domain.api.repository;

import org.boatpos.common.domain.api.repository.MasterDataRepository;
import org.regkas.domain.api.builder.TaxSetBuilder;
import org.regkas.domain.api.model.TaxSet;
import org.regkas.domain.api.values.Name;

import java.util.Optional;

/**
 * The repository for the {@link TaxSet}.
 */
public interface TaxSetRepository extends MasterDataRepository<TaxSet, TaxSetBuilder> {

    /**
     * Load all attributes based on the {@link Name}.
     *
     * @param name the {@link Name} of the {@link TaxSet}
     * @return the current {@link TaxSet} with all attributes from the repository
     */
    Optional<TaxSet> loadBy(Name name);
}
