package org.regkas.repository.api.repository;

import org.boatpos.common.repository.api.repository.MasterDataRepositoryWithDto;
import org.regkas.repository.api.builder.ProductBuilder;
import org.regkas.repository.api.builder.ProductGroupBuilder;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.values.Name;

import java.util.List;
import java.util.Optional;

/**
 * The repository for the {@link ProductGroup}.
 */
public interface ProductGroupRepository extends MasterDataRepositoryWithDto<ProductGroup, ProductGroupBuilder> {

    /**
     * Load all attributes based on the {@link Name}.
     *
     * @param name the {@link Name} of the {@link ProductGroup}
     * @return the current {@link ProductGroup} with all attributes from the repository
     */
    Optional<ProductGroup> loadBy(Name name);
}
