package org.boatpos.common.repository.api.model;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Version;
import org.boatpos.common.service.api.bean.AbstractBean;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

/**
 * The generic domain-model with a dto.
 */
public interface DomainModelWithDto<MODEL extends DomainModelWithDto, ENTITY extends AbstractEntity, DTO extends AbstractBeanBasedOnEntity> extends DomainModel<MODEL, ENTITY> {

    DTO asDto();
}
