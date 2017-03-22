package org.boatpos.common.domain.core.boat;

import org.boatpos.common.domain.api.builder.MasterDataBuilderWithDto;

public interface BoatBuilder extends MasterDataBuilderWithDto<BoatBuilder, Boat, BoatEntity, BoatBean> {

    BoatBuilder add(Name name);

    BoatBuilder add(Price price);

    Class<BoatCore> getDomainModelCoreClass();

    Class<BoatEntity> getEntityClass();
}
