package org.regkas.repository.api.repository;

import org.boatpos.common.repository.api.repository.MasterDataRepository;
import org.regkas.repository.api.builder.ReceiptTypeBuilder;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.values.Name;

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

    /**
     * Get the null-type, e. g. the name is 'Null-Beleg'.
     *
     * @return the null-type
     */
    ReceiptType loadNullType();
}
