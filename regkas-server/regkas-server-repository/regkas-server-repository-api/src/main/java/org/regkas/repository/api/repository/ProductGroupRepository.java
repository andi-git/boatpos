package org.regkas.repository.api.repository;

import org.boatpos.common.repository.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.common.repository.api.values.Enabled;
import org.regkas.repository.api.builder.ProductGroupBuilder;
import org.regkas.repository.api.model.Company;
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

    /**
     * Load all attributes based on the {@link Name} and {@link Company}.
     *
     * @param name    the {@link Name} of the {@link ProductGroup}
     * @param company the {@link Company} the {@link ProductGroup}s belong to
     * @return the current {@link ProductGroup} with all attributes from the repository
     */
    Optional<ProductGroup> loadBy(Name name, Company company);

    /**
     * Load all {@link ProductGroup}s which belongs to the {@link Company}.
     *
     * @return all {@link ProductGroup}s which belongs to the {@link Company}
     */
    List<ProductGroup> loadBy(Company company);

    /**
     * Load all {@link ProductGroup}s which belongs to the {@link Company}.
     *
     * @param company the {@link Company} the {@link ProductGroup}s belong to
     * @param enabled flag if the products should enabled or disabled
     * @return all {@link ProductGroup}s which belongs to the {@link Company}
     */
    List<ProductGroup> loadBy(Company company, Enabled enabled);
}
