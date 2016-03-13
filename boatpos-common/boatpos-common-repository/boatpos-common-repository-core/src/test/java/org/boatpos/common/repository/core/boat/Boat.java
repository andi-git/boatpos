package org.boatpos.common.repository.core.boat;

import org.boatpos.common.repository.api.model.MasterData;

public interface Boat extends MasterData<Boat, BoatEntity, BoatBean> {

    Name getName();

    Boat setName(Name name);

    Price getPrice();

    Boat setPrice(Price price);
}
