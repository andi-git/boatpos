package org.boatpos.dao.api;

import org.boatpos.model.Promotion;

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
}
