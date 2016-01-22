package org.boatpos.repository.api.model;

import org.boatpos.model.AbstractMasterDataEntity;
import org.boatpos.repository.api.values.Enabled;
import org.boatpos.repository.api.values.KeyBinding;
import org.boatpos.repository.api.values.Priority;
import org.boatpos.service.api.bean.AbstractMasterDataBean;

/**
 * The abstract domain model for master data.
 */
public interface MasterData<MODEL extends MasterData, ENTITY extends AbstractMasterDataEntity, DTO extends AbstractMasterDataBean> extends DomainModel<MODEL, ENTITY, DTO> {

    MODEL enable();

    MODEL disable();

    Enabled isEnabled();

    MODEL setEnabled(Enabled enabled);

    Priority getPriority();

    MODEL setPriority(Priority priority);

    KeyBinding getKeyBinding();

    MODEL setKeyBinding(KeyBinding keyBinding);
}
