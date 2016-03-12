package org.boatpos.repository.api.model;

import org.boatpos.common.repository.api.model.MasterData;
import org.boatpos.model.BoatEntity;
import org.boatpos.repository.api.values.*;
import org.boatpos.service.api.bean.BoatBean;

import java.util.Set;

/**
 * The domain model for a boat.
 */
public interface Boat extends MasterData<Boat, BoatEntity, BoatBean>, ContainsRentals<Boat> {

    Name getName();

    Boat setName(Name name);

    ShortName getShortName();

    Boat setShortName(ShortName shortName);

    PriceOneHour getPriceOneHour();

    Boat setPriceOneHour(PriceOneHour priceOneHour);

    PriceThirtyMinutes getPriceThirtyMinutes();

    Boat setPriceThirtyMinutes(PriceThirtyMinutes priceThirtyMinutes);

    PriceFortyFiveMinutes getPriceFortyFiveMinutes();

    Boat setPriceFortyFiveMinutes(PriceFortyFiveMinutes priceFortyFiveMinutes);

    Count getCount();

    Boat setCount(Count count);
}
