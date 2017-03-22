package org.boatpos.common.domain.api.model;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

/**
 * The generic domain-model with a dto.
 */
public interface DomainModelWithDto<MODEL extends DomainModelWithDto, ENTITY extends AbstractEntity, DTO extends AbstractBeanBasedOnEntity> extends DomainModel<MODEL, ENTITY> {

    DTO asDto();
}
