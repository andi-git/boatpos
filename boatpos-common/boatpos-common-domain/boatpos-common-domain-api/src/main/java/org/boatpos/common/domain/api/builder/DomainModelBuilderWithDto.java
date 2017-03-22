package org.boatpos.common.domain.api.builder;

import org.boatpos.common.domain.api.model.DomainModel;
import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

/**
 * Builder for {@link DomainModel} with dto.
 */
public interface DomainModelBuilderWithDto<BUILDER extends DomainModelBuilderWithDto, MODEL extends DomainModel, ENTITY extends AbstractEntity, DTO extends AbstractBeanBasedOnEntity> extends DomainModelBuilder<BUILDER, MODEL, ENTITY> {

    MODEL from(DTO dto);
}
