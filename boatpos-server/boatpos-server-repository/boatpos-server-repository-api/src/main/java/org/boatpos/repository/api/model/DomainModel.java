package org.boatpos.repository.api.model;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.repository.api.values.DomainId;
import org.boatpos.repository.api.values.Version;
import org.boatpos.service.api.bean.AbstractBean;

/**
 * The generic domain-model.
 */
public interface DomainModel<MODEL extends DomainModel, ENTITY extends AbstractEntity, DTO extends AbstractBean> {

    /**
     * Persist (save or update) the current {@link DomainModel}.
     *
     * @return the saved {@link DomainModel}
     */
    MODEL persist();

    /**
     * Delete the current {@link DomainModel}.
     */
    void delete();

    ENTITY asEntity();

    DTO asDto();

    DomainId getId();

    MODEL setId(DomainId id);

    Version getVersion();

    MODEL setVersion(Version version);
}
