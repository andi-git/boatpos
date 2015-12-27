package org.boatpos.repository.api.builder;

import org.boatpos.model.AbstractEntity;
import org.boatpos.repository.api.model.DomainModel;
import org.boatpos.repository.api.values.DomainId;
import org.boatpos.repository.api.values.Version;
import org.boatpos.service.api.bean.AbstractBeanBasedOnEntity;

/**
 * Builder for {@link DomainModel}.
 */
public interface DomainModelBuilder<BUILDER extends DomainModelBuilder, MODEL extends DomainModel, ENTITY extends AbstractEntity, DTO extends AbstractBeanBasedOnEntity> {

    MODEL build();

    MODEL from(ENTITY entity);

    MODEL from(DTO dto);

    BUILDER add(DomainId id);

    BUILDER add(Version version);
}
