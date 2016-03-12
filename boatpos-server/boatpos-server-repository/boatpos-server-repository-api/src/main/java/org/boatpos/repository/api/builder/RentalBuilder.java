package org.boatpos.repository.api.builder;

import org.boatpos.common.repository.api.builder.DomainModelBuilder;
import org.boatpos.model.PaymentMethod;
import org.boatpos.model.RentalEntity;
import org.boatpos.repository.api.model.*;
import org.boatpos.repository.api.values.*;
import org.boatpos.service.api.bean.RentalBean;

import java.util.Set;

/**
 * Builder for {@link Rental}.
 */
public interface RentalBuilder extends DomainModelBuilder<RentalBuilder, Rental, RentalEntity, RentalBean> {

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
