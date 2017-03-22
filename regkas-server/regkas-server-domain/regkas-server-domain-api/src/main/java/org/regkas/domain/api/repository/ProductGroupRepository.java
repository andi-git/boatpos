package org.regkas.domain.api.repository;

import org.boatpos.common.domain.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.common.domain.api.values.Enabled;
import org.regkas.domain.api.builder.ProductGroupBuilder;
import org.regkas.domain.api.model.Company;
import org.regkas.domain.api.model.ProductGroup;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.model.CashBox;

import java.util.List;
import java.util.Optional;

/**
 * The repository for the {@link ProductGroup}.
 */
public interface ProductGroupRepository extends MasterDataRepositoryWithDto<ProductGroup, ProductGroupBuilder> {

    /**
     * Load all attributes based on the {@link Name} and {@link Company}.
     *
     * @param name    the {@link Name} of the {@link ProductGroup}
     * @param cashBox the {@link CashBox} the {@link ProductGroup}s belong to
     * @return the current {@link ProductGroup} with all attributes from the repository
     */
    Optional<ProductGroup> loadBy(Name name, CashBox cashBox);

    /**
     * Load all {@link ProductGroup}s which belongs to the {@link CashBox}.
     *
     * @return all {@link ProductGroup}s which belongs to the {@link Company}
     */
    List<ProductGroup> loadBy(CashBox cashBox);

    /**
     * Load all {@link ProductGroup}s which belongs to the {@link CashBox}.
     *
     * @param cashBox the {@link CashBox} the {@link ProductGroup}s belong to
     * @param enabled flag if the products should enabled or disabled
     * @return all {@link ProductGroup}s which belongs to the {@link CashBox}
     */
    List<ProductGroup> loadBy(CashBox cashBox, Enabled enabled);
}
