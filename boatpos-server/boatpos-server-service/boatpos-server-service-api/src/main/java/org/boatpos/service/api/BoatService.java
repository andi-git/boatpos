package org.boatpos.service.api;

import org.boatpos.service.api.bean.BoatBean;
import org.boatpos.service.api.bean.BoatCountSummary;

import java.util.Optional;

/**
 * Service for {@link BoatBean}s.
 */
public interface BoatService extends MasterDataService<BoatBean> {

    /**
     * Get a {@link BoatBean} by it's name.
     *
     * @param name the name of the {@link BoatBean}
     * @return the {@link BoatBean} or {@code null} if it is not available
     */
    Optional<BoatBean> getByName(String name);

    /**
     * Get a {@link BoatBean} by it's short-name.
     *
     * @param shortName the short-name of the {@link BoatBean}
     * @return the {@link BoatBean} or {@code null} if it is not available
     */
    Optional<BoatBean> getByShortName(String shortName);

    /**
     * Count all available boats.
     *
     * @return a summary of available boats.
     */
    BoatCountSummary countBoats();
}
