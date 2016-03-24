package org.boatpos.common.repository.api.builder;

import org.boatpos.common.model.AbstractMasterDataEntity;
import org.boatpos.common.repository.api.model.MasterData;
import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

/**
 * Builder for {@link MasterData}.
 */
public interface MasterDataBuilderWithDto<BUILDER extends MasterDataBuilderWithDto, MODEL extends MasterData, ENTITY extends AbstractMasterDataEntity, DTO extends AbstractBeanBasedOnEntity>
        extends DomainModelBuilderWithDto<BUILDER, MODEL, ENTITY, DTO>, MasterDataBuilder<BUILDER, MODEL, ENTITY> {

    BUILDER add(Enabled enabled);

    BUILDER add(Priority priority);

    BUILDER add(KeyBinding keyBinding);

    BUILDER add(PictureUrlThumb pictureUrlThumb);

    BUILDER add(PictureUrl pictureUrl);
}
