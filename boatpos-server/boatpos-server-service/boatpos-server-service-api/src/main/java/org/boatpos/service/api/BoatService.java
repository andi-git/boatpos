package org.boatpos.service.api;

import org.boatpos.service.api.bean.BoatBean;

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
    BoatBean getByName(String name);

    /**
     * Get a {@link BoatBean} by it's short-name.
     *
     * @param shortName the short-name of the {@link BoatBean}
     * @return the {@link BoatBean} or {@code null} if it is not available
     */
    BoatBean getByShortName(String shortName);

    /**
     * Save a new {@link BoatBean}. The {@link BoatBean#id} must no be set.
     *
     * @param boatBean the {@link BoatBean} to save
     * @return the saved {@link BoatBean} extended with the id
     */
    BoatBean save(BoatBean boatBean);

    /**
     * Update an existing {@link BoatBean}. The {@link BoatBean#id} must be set and valid.
     *
     * @param boatBean the {@link BoatBean} to update
     * @return the updated {@link BoatBean}
     */
    BoatBean update(BoatBean boatBean);
}
