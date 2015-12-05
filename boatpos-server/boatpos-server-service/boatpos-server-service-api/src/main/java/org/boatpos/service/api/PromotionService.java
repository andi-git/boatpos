package org.boatpos.service.api;

import org.boatpos.service.api.bean.PromotionBean;

import java.util.List;

/**
 * Service for {@link PromotionBean}.
 */
public interface PromotionService {

    /**
     * Get a {@link List} of all {@link PromotionBean}s ordered by {@link PromotionBean#priority}.
     *
     * @return a {@link List} of all {@link PromotionBean}s ordered by {@link PromotionBean#priority}
     */
    List<PromotionBean> getAll();

    /**
     * Get a {@link List} of all {@link PromotionBean}s which are relevant <u>before</u> the rental.
     *
     * @return a {@link List} of all {@link PromotionBean}s which are relevant <u>before</u> the rental
     */
    List<PromotionBean> getAllBeforeRental();

    /**
     * Get a {@link List} of all {@link PromotionBean}s which are relevant <u>after</u> the rental.
     *
     * @return a {@link List} of all {@link PromotionBean}s which are relevant <u>after</u> the rental
     */
    List<PromotionBean> getAllAfterRental();

    /**
     * Get a {@link PromotionBean} by it's id.
     *
     * @param id the id of the {@link PromotionBean}
     * @return the {@link PromotionBean} or {@code null} if the id is not available
     */
    PromotionBean getById(Long id);

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
     * Delete an existing {@link PromotionBean} via the (valid) id.
     *
     * @param id the id of the {@link PromotionBean} to delete
     * @return {@code true} if the operation was successful
     */
    boolean delete(Long id);
}
