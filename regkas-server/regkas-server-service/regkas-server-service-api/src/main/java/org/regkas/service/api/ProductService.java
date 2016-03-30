package org.regkas.service.api;

import org.boatpos.common.service.api.EnabledState;
import org.boatpos.common.service.api.MasterDataService;
import org.regkas.service.api.bean.ProductBean;

import java.util.List;

/**
 * The service for the product.
 */
public interface ProductService extends MasterDataService<ProductBean> {

    /**
     * Get a {@link List} of all {@link ProductBean}s for the current company ordered by {@code priority}.
     *
     * @return a {@link List} of all {@link ProductBean}s for the current company ordered by {@code priority}
     */
    List<ProductBean> getAllForCurrentCompany();

    /**
     * Get a {@link List} of all {@link ProductBean}s for the current company ordered by {@code priority}.
     *
     * @param enabledState the {@link EnabledState} of the beans to search
     * @return a {@link List} of all {@link ProductBean}s for the current company ordered by {@code priority}
     */
    List<ProductBean> getAllForCurrentCompany(EnabledState enabledState);
}
