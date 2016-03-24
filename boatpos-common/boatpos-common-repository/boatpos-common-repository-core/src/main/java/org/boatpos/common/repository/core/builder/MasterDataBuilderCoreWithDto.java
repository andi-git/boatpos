package org.boatpos.common.repository.core.builder;

import org.boatpos.common.model.AbstractMasterDataEntity;
import org.boatpos.common.repository.api.builder.MasterDataBuilderWithDto;
import org.boatpos.common.repository.api.model.MasterData;
import org.boatpos.common.repository.core.model.MasterDataCore;
import org.boatpos.common.service.api.bean.AbstractMasterDataBean;

import javax.inject.Inject;

public abstract class MasterDataBuilderCoreWithDto<BUILDER extends MasterDataBuilderWithDto, MODEL extends MasterData, MODELCORE extends MasterDataCore, ENTITY extends AbstractMasterDataEntity, DTO extends AbstractMasterDataBean>
        extends MasterDataBuilderCore<BUILDER, MODEL, MODELCORE, ENTITY>
        implements MasterDataBuilderWithDto<BUILDER, MODEL, ENTITY, DTO> {

    @Override
    public MODEL from(DTO dto) {
        return DtoToModelConverter.from(dto, getDomainModelCoreClass());
    }
}
