package org.regkas.repository.api.repository;

import org.boatpos.common.repository.api.repository.MasterDataRepository;
import org.regkas.repository.api.builder.TaxSetBuilder;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.values.Name;

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
