package org.boatpos.common.domain.api.repository;

import org.boatpos.common.domain.api.builder.MasterDataBuilderWithDto;
import org.boatpos.common.domain.api.model.MasterData;
import org.boatpos.common.domain.api.model.MasterDataWithDto;

/**
 * The repository for the {@link MasterData}.
 */
public interface MasterDataRepositoryWithDto<MODEL extends MasterDataWithDto, BUILDER extends MasterDataBuilderWithDto> extends MasterDataRepository<MODEL, BUILDER> {

}
