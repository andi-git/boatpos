package org.regkas.repository.api.repository;

import org.boatpos.common.repository.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.common.repository.api.values.Enabled;
import org.regkas.repository.api.builder.ProductBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Company;
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
     * Load the generic {@link Product} for a {@link ProductGroup}.
     *
     * @param productGroup the {@link ProductGroup} to load the generic {@link Product} for
     * @return the generic {@link Product} for a {@link ProductGroup}
     */
    Optional<Product> loadGenericBy(ProductGroup productGroup);

    /**
     * Load all attributes based on the {@link Name} and {@link Company}.
     *
     * @param name    the {@link Name} of the {@link Product}
     * @param cashBox the {@link CashBox} the {@link Product}s belong to
     * @return the current {@link Product} with all attributes from the repository
     */
    Optional<Product> loadBy(Name name, CashBox cashBox);

    /**
     * Load all {@link Product}s which belongs to the {@link CashBox}.
     *
     * @param cashBox the {@link CashBox} the {@link Product}s belong to
     * @return all {@link Product}s which belongs to the {@link CashBox}
     */
    List<Product> loadBy(CashBox cashBox);

    /**
     * Load all {@link Product}s which belongs to the {@link CashBox}.
     *
     * @param cashBox the {@link CashBox} the {@link Product}s belong to
     * @param enabled flag if the products should enabled or disabled
     * @return all {@link Product}s which belongs to the {@link CashBox}
     */
    List<Product> loadBy(CashBox cashBox, Enabled enabled);

    /**
     * Load all {@link Product}s of a {@link ProductGroup}.
     *
     * @param productGroup the {@link ProductGroup} of the {@link Product}
     * @return all {@link Product}s of the {@link ProductGroup}
     */
    List<Product> loadBy(ProductGroup productGroup);
}
