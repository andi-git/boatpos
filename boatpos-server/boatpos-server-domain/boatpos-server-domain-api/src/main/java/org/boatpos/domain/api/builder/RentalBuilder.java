package org.boatpos.domain.api.builder;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.domain.api.builder.DomainModelBuilderWithDto;
import org.boatpos.domain.api.model.Boat;
import org.boatpos.domain.api.model.Commitment;
import org.boatpos.domain.api.model.PromotionBefore;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.api.values.Day;
import org.boatpos.domain.api.values.DayId;
import org.boatpos.domain.api.values.DepartureTime;
import org.boatpos.domain.api.values.PriceCalculatedBefore;
import org.boatpos.model.RentalEntity;
import org.boatpos.service.api.bean.RentalBean;

import java.util.Set;

/**
 * Builder for {@link Rental}.
 */
public interface RentalBuilder extends DomainModelBuilderWithDto<RentalBuilder, Rental, RentalEntity, RentalBean> {

    RentalBuilder add(DayId dayId);

    RentalBuilder add(Day day);

    RentalBuilder add(Boat boat);

    RentalBuilder add(DepartureTime departureTime);

    RentalBuilder add(PromotionBefore promotion);

    RentalBuilder add(Set<Commitment> commitments);

    RentalBuilder add(Commitment commitment);

    RentalBuilder add(PriceCalculatedBefore priceCalculatedBefore);

    RentalBuilder add(PaymentMethod paymentMethodBefore);
}
