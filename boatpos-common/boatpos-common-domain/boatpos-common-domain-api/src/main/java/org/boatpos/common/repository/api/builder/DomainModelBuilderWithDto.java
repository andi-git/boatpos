package org.boatpos.common.repository.api.builder;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Version;
import org.boatpos.common.service.api.bean.AbstractBean;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

/**
 * Builder for {@link DomainModel} with dto.
 */
public interface DomainModelBuilderWithDto<BUILDER extends DomainModelBuilderWithDto, MODEL extends DomainModel, ENTITY extends AbstractEntity, DTO extends AbstractBeanBasedOnEntity> extends DomainModelBuilder<BUILDER, MODEL, ENTITY> {

    MODEL from(DTO dto);
}
