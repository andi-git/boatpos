package org.boatpos.common.domain.api.model;

import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.model.AbstractMasterDataEntity;

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
