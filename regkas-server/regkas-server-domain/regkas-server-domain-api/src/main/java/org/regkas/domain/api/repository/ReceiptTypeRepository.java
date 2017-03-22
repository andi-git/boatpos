package org.regkas.domain.api.repository;

import org.boatpos.common.domain.api.repository.MasterDataRepository;
import org.regkas.domain.api.builder.ReceiptTypeBuilder;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.values.Name;

import java.util.Optional;

/**
 * The repository for the {@link ReceiptType}.
 */
public interface ReceiptTypeRepository extends MasterDataRepository<ReceiptType, ReceiptTypeBuilder> {

    /**
     * Load all attributes based on the {@link Name}.
     *
     * @param name the {@link Name} of the {@link ReceiptType}
     * @return the current {@link ReceiptType} with all attributes from the repository
     */
    Optional<ReceiptType> loadBy(Name name);
}
