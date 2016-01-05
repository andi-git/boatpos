package org.boatpos.repository.api.builder;

import org.boatpos.model.BoatEntity;
import org.boatpos.model.RentalEntity;
import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.values.*;
import org.boatpos.service.api.bean.BoatBean;

import java.util.Set;

/**
 * Builder for {@link Boat}.
 */
public interface BoatBuilder extends MasterDataBuilder<BoatBuilder, Boat, BoatEntity, BoatBean> {

    BoatBuilder add(Name name);

    BoatBuilder add(ShortName shortName);

    BoatBuilder add(PriceOneHour priceOneHour);

    BoatBuilder add(PriceThirtyMinutes priceThirtyMinutes);

    BoatBuilder add(PriceFortyFiveMinutes priceFortyFiveMinutes);

    BoatBuilder add(Count count);

    BoatBuilder add(Set<Rental> rentals);

    BoatBuilder add(Rental rental);

    BoatBuilder add(PictureUrlSmall pictureUrlSmall);

    BoatBuilder add(PictureUrlMedium pictureUrlMedium);

    BoatBuilder add(PictureUrlLarge pictureUrlLarge);
}
