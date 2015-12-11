package org.boatpos.service.api;

import org.boatpos.service.api.bean.AbstractMasterDataBean;

import java.util.List;

/**
 * The enable- / disable-state of the bean.
 *
 * @param <DTO> the type of the DTO
 */
public interface MasterDataService<DTO extends AbstractMasterDataBean> {

    /**
     * Get a {@link DTO} by it's id.
     *
     * @param id the id of the {@link DTO}
     * @return the {@link DTO} or {@code null} if the id is not available
     */
    DTO getById(Long id);


    /**
     * Get a {@link List} of all {@link DTO}s ordered by its {@code priority}.
     *
     * @return a {@link List} of all {@link DTO}s ordered by {@code priority}
     */
    List<DTO> getAll();

    /**
     * Get a {@link List} of all {@link DTO}s ordered by {@code priority}.
     *
     * @param enabledState the {@link EnabledState} of the beans to search
     * @return a {@link List} of all {@link DTO}s ordered by {@code priority}
     */
    List<DTO> getAll(EnabledState enabledState);

    /**
     * Enable the bean.
     *
     * @param id the id of the bean to enable
     */
    void enable(Long id);

    /**
     * Disable the bean.
     *
     * @param id the id of the bean to disable
     */
    void disable(Long id);

}
