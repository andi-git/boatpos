package org.boatpos.common.repository.core.builder;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.repository.api.builder.DomainModelBuilderWithDto;
import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.core.model.DomainModelCore;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

import javax.inject.Inject;

public abstract class DomainModelBuilderWithDtoCore<BUILDER extends DomainModelBuilderWithDto, MODEL extends DomainModel, MODELCORE extends DomainModelCore, ENTITY extends AbstractEntity, DTO extends AbstractBeanBasedOnEntity>
        extends DomainModelBuilderCore<BUILDER, MODEL, MODELCORE, ENTITY>
        implements DomainModelBuilderWithDto<BUILDER, MODEL, ENTITY, DTO> {

    @Override
    public MODEL from(DTO dto) {
        return DtoToModelConverter.from(dto, getDomainModelCoreClass());
    }
}
