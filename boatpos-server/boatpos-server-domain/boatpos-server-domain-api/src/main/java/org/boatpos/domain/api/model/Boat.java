package org.boatpos.domain.api.model;

import org.boatpos.common.domain.api.model.MasterDataWithDto;
import org.boatpos.domain.api.values.Count;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.api.values.PriceFortyFiveMinutes;
import org.boatpos.domain.api.values.PriceOneHour;
import org.boatpos.domain.api.values.PriceThirtyMinutes;
import org.boatpos.domain.api.values.ShortName;
import org.boatpos.model.BoatEntity;
import org.boatpos.service.api.bean.BoatBean;

/**
 * The domain model for a boat.
 */
public interface Boat extends MasterDataWithDto<Boat, BoatEntity, BoatBean>, ContainsRentals<Boat> {

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
