package org.boatpos.domain.api.builder;

import org.boatpos.common.domain.api.builder.MasterDataBuilderWithDto;
import org.boatpos.domain.api.values.Count;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.api.values.PriceFortyFiveMinutes;
import org.boatpos.domain.api.values.PriceOneHour;
import org.boatpos.domain.api.values.PriceThirtyMinutes;
import org.boatpos.domain.api.values.ShortName;
import org.boatpos.model.BoatEntity;
import org.boatpos.domain.api.model.Boat;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.service.api.bean.BoatBean;

import java.util.Set;

/**
 * Builder for {@link Boat}.
 */
public interface BoatBuilder extends MasterDataBuilderWithDto<BoatBuilder, Boat, BoatEntity, BoatBean> {

    BoatBuilder add(Name name);

    BoatBuilder add(ShortName shortName);

    BoatBuilder add(PriceOneHour priceOneHour);

    BoatBuilder add(PriceThirtyMinutes priceThirtyMinutes);

    BoatBuilder add(PriceFortyFiveMinutes priceFortyFiveMinutes);

    BoatBuilder add(Count count);

    BoatBuilder add(Set<Rental> rentals);

    BoatBuilder add(Rental rental);
}
