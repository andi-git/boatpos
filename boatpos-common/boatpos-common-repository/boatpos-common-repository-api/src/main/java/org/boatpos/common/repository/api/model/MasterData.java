package org.boatpos.common.repository.api.model;

import org.boatpos.common.model.AbstractMasterDataEntity;
import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.service.api.bean.AbstractMasterDataBean;

/**
 * The abstract domain model for master data.
 */
public interface MasterData<MODEL extends MasterData, ENTITY extends AbstractMasterDataEntity> extends DomainModel<MODEL, ENTITY> {

    MODEL enable();

    MODEL disable();

    Enabled isEnabled();

    MODEL setEnabled(Enabled enabled);

    Priority getPriority();

    MODEL setPriority(Priority priority);

    KeyBinding getKeyBinding();

    MODEL setKeyBinding(KeyBinding keyBinding);

    PictureUrlThumb getPictureUrlThumb();

    MODEL setPictureUrlThumb(PictureUrlThumb pictureUrlThumb);

    PictureUrl getPictureUrl();

    MODEL setPictureUrl(PictureUrl pictureUrl);
}
