package org.boatpos.common.domain.api.builder;

import org.boatpos.common.domain.api.model.MasterData;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.model.AbstractMasterDataEntity;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

/**
 * Builder for {@link MasterData}.
 */
public interface MasterDataBuilderWithDto<BUILDER extends MasterDataBuilderWithDto, MODEL extends MasterData, ENTITY extends AbstractMasterDataEntity, DTO extends AbstractBeanBasedOnEntity>
        extends DomainModelBuilderWithDto<BUILDER, MODEL, ENTITY, DTO>, MasterDataBuilder<BUILDER, MODEL, ENTITY> {

    @Override
    BUILDER add(Enabled enabled);

    @Override
    BUILDER add(Priority priority);

    @Override
    BUILDER add(KeyBinding keyBinding);

    @Override
    BUILDER add(PictureUrlThumb pictureUrlThumb);

    @Override
    BUILDER add(PictureUrl pictureUrl);
}
