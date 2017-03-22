package org.boatpos.common.domain.core.builder;

import org.boatpos.common.model.AbstractMasterDataEntity;
import org.boatpos.common.domain.api.builder.MasterDataBuilderWithDto;
import org.boatpos.common.domain.api.model.MasterData;
import org.boatpos.common.domain.core.model.MasterDataCore;
import org.boatpos.common.service.api.bean.AbstractMasterDataBean;

public abstract class MasterDataBuilderCoreWithDto<BUILDER extends MasterDataBuilderWithDto, MODEL extends MasterData, MODELCORE extends MasterDataCore, ENTITY extends AbstractMasterDataEntity, DTO extends AbstractMasterDataBean>
        extends MasterDataBuilderCore<BUILDER, MODEL, MODELCORE, ENTITY>
        implements MasterDataBuilderWithDto<BUILDER, MODEL, ENTITY, DTO> {

    @Override
    public MODEL from(DTO dto) {
        return DtoToModelConverter.from(dto, getDomainModelCoreClass());
    }
}
