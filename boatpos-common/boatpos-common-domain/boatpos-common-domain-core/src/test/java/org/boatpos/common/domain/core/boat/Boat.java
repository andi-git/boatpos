package org.boatpos.common.domain.core.boat;

import org.boatpos.common.domain.api.model.MasterDataWithDto;

public interface Boat extends MasterDataWithDto<Boat, BoatEntity, BoatBean> {

    Name getName();

    Boat setName(Name name);

    Price getPrice();

    Boat setPrice(Price price);
}
