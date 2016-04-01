package org.regkas.service.api;

import org.boatpos.common.service.api.EnabledState;
import org.boatpos.common.service.api.MasterDataService;
import org.regkas.service.api.bean.ProductBean;
import org.regkas.service.api.bean.ProductGroupBean;

import java.util.List;

/**
 * The service for the product-group.
 */
public interface ProductGroupService extends MasterDataService<ProductGroupBean> {


    /**
     * Get a the {@link ProductGroupBean} for the current company by it's name.
     *
     * @param name the name of the product
     * @return the {@link ProductGroupBean} for the name for the current company
     */
    ProductGroupBean getForCurrentCompany(String name);

    /**
     * Get a the generic {@link ProductBean} for a product-group
     *
     * @param productGroupName the name of the productGroup
     * @return the generic {@link ProductBean} for a product-group
     */
    ProductBean getGenericProductFor(String productGroupName);

    /**
     * Get a {@link List} of all {@link ProductGroupBean}s for the current company ordered by {@code priority}.
     *
     * @return a {@link List} of all {@link ProductGroupBean}s for the current company ordered by {@code priority}
     */
    List<ProductGroupBean> getAllForCurrentCompany();

    /**
     * Get a {@link List} of all {@link ProductGroupBean}s for the current company ordered by {@code priority}.
     *
     * @param enabledState the {@link EnabledState} of the beans to search
     * @return a {@link List} of all {@link ProductGroupBean}s for the current company ordered by {@code priority}
     */
    List<ProductGroupBean> getAllForCurrentCompany(EnabledState enabledState);
}
