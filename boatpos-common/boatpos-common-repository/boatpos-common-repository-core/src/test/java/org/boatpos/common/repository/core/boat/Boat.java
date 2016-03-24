package org.boatpos.common.repository.core.boat;

import org.boatpos.common.repository.api.model.MasterDataWithDto;

public interface Boat extends MasterDataWithDto<Boat, BoatEntity, BoatBean> {

    Name getName();

    Boat setName(Name name);

    Price getPrice();

    Boat setPrice(Price price);
}
