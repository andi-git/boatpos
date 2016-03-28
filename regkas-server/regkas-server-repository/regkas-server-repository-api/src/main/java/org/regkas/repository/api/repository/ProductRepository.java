package org.regkas.repository.api.repository;

import org.boatpos.common.repository.api.repository.MasterDataRepositoryWithDto;
import org.regkas.repository.api.builder.ProductBuilder;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.values.Name;

import java.util.List;
import java.util.Optional;

/**
 * The repository for the {@link Product}.
 */
public interface ProductRepository extends MasterDataRepositoryWithDto<Product, ProductBuilder> {

    /**
     * Load all attributes based on the {@link Name}.
     *
     * @param name the {@link Name} of the {@link Product}
     * @return the current {@link Product} with all attributes from the repository
     */
    Optional<Product> loadBy(Name name);

    /**
     * Load all {@link Product}s of a {@link ProductGroup}.
     *
     * @param productGroup the {@link ProductGroup} of the {@link Product}
     * @return all {@link Product}s of the {@link ProductGroup}
     */
    List<Product> loadBy(ProductGroup productGroup);
}