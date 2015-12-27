package org.boatpos.repository.api.builder;

import org.boatpos.model.AbstractMasterDataEntity;
import org.boatpos.repository.api.model.MasterData;
import org.boatpos.repository.api.values.Enabled;
import org.boatpos.repository.api.values.Priority;
import org.boatpos.service.api.bean.AbstractMasterDataBean;

/**
 * Builder for {@link MasterData}.
 */
public interface MasterDataBuilder<BUILDER extends MasterDataBuilder, MODEL extends MasterData, ENTITY extends AbstractMasterDataEntity, DTO extends AbstractMasterDataBean> extends DomainModelBuilder<BUILDER, MODEL, ENTITY, DTO> {

    BUILDER add(Enabled enabled);

    BUILDER add(Priority priority);
}
