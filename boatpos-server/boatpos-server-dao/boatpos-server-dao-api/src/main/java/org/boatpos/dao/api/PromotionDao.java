package org.boatpos.dao.api;

import org.boatpos.model.Promotion;
import org.boatpos.model.PromotionAfter;
import org.boatpos.model.PromotionBefore;

import java.util.List;
import java.util.Optional;

/**
 * The DAO for {@link Promotion}.
 */
public interface PromotionDao extends GenericDao<Promotion> {

    /**
     * Get a {@link Promotion} by it's {@link Promotion#name}.
     *
     * @param name the name of the {@link Promotion}
     * @return an {@link Optional} of {@link Promotion}
     */
    Optional<Promotion> getByName(String name);

    /**
     * Get a {@link List} of all {@link Promotion}s ordered by {@link Promotion#priority}.
     *
     * @return a {@link List} of all {@link Promotion}s ordered by {@link Promotion#priority}
     */
    List<Promotion> getAll();

    /**
     * Get a {@link List} of all {@link PromotionBefore}s which are relevant <u>before</u> the rental.
     *
     * @return a {@link List} of all {@link PromotionBefore}s which are relevant <u>before</u> the rental
     */
    List<PromotionBefore> getAllBeforeRental();

    /**
     * Get a {@link List} of all {@link PromotionAfter}s which are relevant <u>after</u> the rental.
     *
     * @return a {@link List} of all {@link PromotionAfter}s which are relevant <u>after</u> the rental
     */
    List<PromotionAfter> getAllAfterRental();
}
