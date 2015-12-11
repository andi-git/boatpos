package org.boatpos.service.api;

import org.boatpos.service.api.bean.CommitmentBean;

import java.util.List;

/**
 * Service for {@link CommitmentBean}s.
 */
public interface CommitmentService extends MasterDataService<CommitmentBean> {

    /**
     * Get a {@link List} of all {@link CommitmentBean}s ordered by {@link CommitmentBean#priority}.
     *
     * @param enabledState the {@link EnabledState} of the beans to search
     * @return a {@link List} of all {@link CommitmentBean}s ordered by {@link CommitmentBean#priority}
     */
    List<CommitmentBean> getAll(EnabledState enabledState);

    /**
     * Get a {@link CommitmentBean} by it's name.
     *
     * @param name the name of the {@link CommitmentBean}
     * @return the {@link CommitmentBean} or {@code null} if it is not available
     */
    CommitmentBean getByName(String name);

    /**
     * Save a new {@link CommitmentBean}. The {@link CommitmentBean#id} must no be set.
     *
     * @param commitmentBean the {@link CommitmentBean} to save
     * @return the saved {@link CommitmentBean} extended with the id
     */
    CommitmentBean save(CommitmentBean commitmentBean);

    /**
     * Update an existing {@link CommitmentBean}. The {@link CommitmentBean#id} must be set and valid.
     *
     * @param commitmentBean the {@link CommitmentBean} to update
     * @return the updated {@link CommitmentBean}
     */
    CommitmentBean update(CommitmentBean commitmentBean);

    /**
     * Enable a {@link CommitmentBean} (set {@link CommitmentBean#enabled} to {@code true}).
     *
     * @param id the id of the {@link CommitmentBean} to enable
     */
    @Override
    void enable(Long id);

    /**
     * Disable a {@link CommitmentBean} (set {@link CommitmentBean#enabled} to {@code false}).
     *
     * @param id the id of the {@link CommitmentService} to disable
     */
    @Override
    void disable(Long id);

}
