package org.boatpos.common.repository.api.repository;

import org.boatpos.common.repository.api.builder.MasterDataBuilderWithDto;
import org.boatpos.common.repository.api.model.MasterData;
import org.boatpos.common.repository.api.model.MasterDataWithDto;
import org.boatpos.common.repository.api.values.Enabled;

import java.util.List;

/**
 * The repository for the {@link MasterData}.
 */
public interface MasterDataRepositoryWithDto<MODEL extends MasterDataWithDto, BUILDER extends MasterDataBuilderWithDto> extends MasterDataRepository<MODEL, BUILDER> {

}
