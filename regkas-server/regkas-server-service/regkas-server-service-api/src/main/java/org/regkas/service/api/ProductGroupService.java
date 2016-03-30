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
