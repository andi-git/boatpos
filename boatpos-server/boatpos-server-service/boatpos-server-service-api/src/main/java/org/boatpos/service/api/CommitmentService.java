package org.boatpos.service.api;

import org.boatpos.service.api.bean.CommitmentBean;

import java.util.List;
import java.util.Optional;

/**
 * Service for {@link CommitmentBean}s.
 */
public interface CommitmentService extends MasterDataService<CommitmentBean> {

    /**
     * Get a {@link CommitmentBean} by it's name.
     *
     * @param name the name of the {@link CommitmentBean}
     * @return the {@link CommitmentBean} or {@code null} if it is not available
     */
    Optional<CommitmentBean> getByName(String name);
}
