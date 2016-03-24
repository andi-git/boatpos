package org.boatpos.common.repository.core.boat;

import org.boatpos.common.repository.api.builder.MasterDataBuilderWithDto;

public interface BoatBuilder extends MasterDataBuilderWithDto<BoatBuilder, Boat, BoatEntity, BoatBean> {

    BoatBuilder add(Name name);

    BoatBuilder add(Price price);
}
