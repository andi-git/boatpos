package org.boatpos.service.api;

import org.boatpos.service.api.bean.CommitmentBean;

import java.util.List;

/**
 * Service for {@link CommitmentBean}s.
 */
public interface CommitmentService {

    /**
     * Get a {@link List} of all {@link CommitmentBean}s ordered by {@link CommitmentBean#priority}.
     *
     * @return a {@link List} of all {@link CommitmentBean}s ordered by {@link CommitmentBean#priority}
     */
    List<CommitmentBean> getAll();

    /**
     * Get a {@link CommitmentBean} by it's id.
     *
     * @param id the id of the {@link CommitmentBean}
     * @return the {@link CommitmentBean} or {@code null} if the id is not available
     */
    CommitmentBean getById(Long id);

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
     * Delete an existing {@link CommitmentBean} via the (valid) id.
     *
     * @param id the id of the {@link CommitmentBean} to delete
     */
    void delete(Long id);
}
