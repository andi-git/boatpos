package org.boatpos.common.repository.api.model;

import org.boatpos.common.model.AbstractMasterDataEntity;
import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.service.api.bean.AbstractBean;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

/**
 * The abstract domain model for master data with a dto.
 */
public interface MasterDataWithDto<MODEL extends MasterDataWithDto, ENTITY extends AbstractMasterDataEntity, DTO extends AbstractBeanBasedOnEntity> extends MasterData<MODEL, ENTITY>, DomainModelWithDto<MODEL, ENTITY, DTO> {

}
