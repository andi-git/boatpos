package org.boatpos.common.domain.api.builder;

import org.boatpos.common.domain.api.model.MasterData;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.model.AbstractMasterDataEntity;

/**
 * Builder for {@link MasterData}.
 */
public interface MasterDataBuilder<BUILDER extends MasterDataBuilder, MODEL extends MasterData, ENTITY extends AbstractMasterDataEntity> extends DomainModelBuilder<BUILDER, MODEL, ENTITY> {

    BUILDER add(Enabled enabled);

    BUILDER add(Priority priority);

    BUILDER add(KeyBinding keyBinding);

    BUILDER add(PictureUrlThumb pictureUrlThumb);

    BUILDER add(PictureUrl pictureUrl);
}
