package org.boatpos.service.api;

import org.boatpos.service.api.bean.PromotionAfterBean;
import org.boatpos.service.api.bean.PromotionBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;

import java.util.List;

/**
 * Service for {@link PromotionBean}.
 */
public interface PromotionService extends MasterDataService<PromotionBean> {

    /**
     * Get a {@link List} of all {@link PromotionBean}s ordered by {@link PromotionBean#priority}.
     *
     * @param enabledState the {@link EnabledState} of the beans to search
     * @return a {@link List} of all {@link PromotionBean}s ordered by {@link PromotionBean#priority}
     */
    List<PromotionBean> getAll(EnabledState enabledState);

    /**
     * Get a {@link List} of all {@link PromotionBeforeBean}s which are relevant <u>before</u> the rental.
     *
     * @param enabledState the {@link EnabledState} of the beans to search
     * @return a {@link List} of all {@link PromotionBeforeBean}s which are relevant <u>before</u> the rental
     */
    List<PromotionBeforeBean> getAllBeforeRental(EnabledState enabledState);

    /**
     * Get a {@link List} of all {@link PromotionAfterBean}s which are relevant <u>after</u> the rental.
     *
     * @param enabledState the {@link EnabledState} of the beans to search
     * @return a {@link List} of all {@link PromotionAfterBean}s which are relevant <u>after</u> the rental
     */
    List<PromotionAfterBean> getAllAfterRental(EnabledState enabledState);

    /**
     * Get a {@link PromotionBean} by it's name.
     *
     * @param name the name of the {@link PromotionBean}
     * @return the {@link PromotionBean} or {@code null} if it is not available
     */
    PromotionBean getByName(String name);

    /**
     * Save a new {@link PromotionBean}. The {@link PromotionBean#id} must no be set.
     *
     * @param promotionBean the {@link PromotionBean} to save
     * @return the saved {@link PromotionBean} extended with the id
     */
    PromotionBean save(PromotionBean promotionBean);

    /**
     * Update an existing {@link PromotionBean}. The {@link PromotionBean#id} must be set and valid.
     *
     * @param promotionBean the {@link PromotionBean} to update
     * @return the updated {@link PromotionBean}
     */
    PromotionBean update(PromotionBean promotionBean);


    /**
     * Enable a {@link PromotionBean} (set {@link PromotionBean#enabled} to {@code true}).
     *
     * @param id the id of the {@link PromotionBean} to enable.
     */
    @Override
    void enable(Long id);

    /**
     * Disable a {@link PromotionBean} (set {@link PromotionBean#enabled} to {@code false}).
     *
     * @param id the id of the {@link PromotionBean} to enable.
     */
    @Override
    void disable(Long id);
}
