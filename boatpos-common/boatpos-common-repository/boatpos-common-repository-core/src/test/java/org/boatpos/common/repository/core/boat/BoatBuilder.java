package org.boatpos.common.repository.core.boat;

import org.boatpos.common.repository.api.builder.MasterDataBuilder;

public interface BoatBuilder extends MasterDataBuilder<BoatBuilder, Boat, BoatEntity, BoatBean> {

    BoatBuilder add(Name name);

    BoatBuilder add(Price price);
}
