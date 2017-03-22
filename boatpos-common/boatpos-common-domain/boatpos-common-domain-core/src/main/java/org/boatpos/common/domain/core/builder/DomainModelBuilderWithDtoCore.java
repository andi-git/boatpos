package org.boatpos.common.domain.core.builder;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.domain.api.builder.DomainModelBuilderWithDto;
import org.boatpos.common.domain.api.model.DomainModel;
import org.boatpos.common.domain.core.model.DomainModelCore;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

public abstract class DomainModelBuilderWithDtoCore<BUILDER extends DomainModelBuilderWithDto, MODEL extends DomainModel, MODELCORE extends DomainModelCore, ENTITY extends AbstractEntity, DTO extends AbstractBeanBasedOnEntity>
        extends DomainModelBuilderCore<BUILDER, MODEL, MODELCORE, ENTITY>
        implements DomainModelBuilderWithDto<BUILDER, MODEL, ENTITY, DTO> {

    @Override
    public MODEL from(DTO dto) {
        return DtoToModelConverter.from(dto, getDomainModelCoreClass());
    }
}
