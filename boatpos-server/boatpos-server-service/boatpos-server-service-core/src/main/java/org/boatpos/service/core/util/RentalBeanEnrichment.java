package org.boatpos.service.core.util;

import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.values.ArrivalTime;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.service.core.PriceCalculator;
import org.boatpos.util.datetime.DateTimeHelper;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.temporal.ChronoUnit;

/**
 * Add additional data for {@link RentalBean}.
 */
@Dependent
public class RentalBeanEnrichment {

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Inject
    private PriceCalculator priceCalculator;

    public RentalBean asDto(Rental rental) {
        RentalBean rentalBean = rental.asDto();

        // add time of travel
        ArrivalTime tmpArrivalTime = new ArrivalTime(dateTimeHelper.currentTime());
        if (rental.getArrivalTime().isPresent()) {
            tmpArrivalTime = rental.getArrivalTime();
        }
        int timeOfTravel = (int) rental.getDepartureTime().get().until(tmpArrivalTime.get(), ChronoUnit.MINUTES);
        int timeOfTravelCalculated = timeOfTravel > PriceCalculator.TIME_BONUS_IN_MINUTES ? (int) (timeOfTravel - PriceCalculator.TIME_BONUS_IN_MINUTES) : 0;
        rentalBean.setTimeOfTravel(timeOfTravel);
        rentalBean.setTimeOfTravelCalculated(timeOfTravelCalculated);

        // calculate price if not available
        if (!rental.getPriceCalculatedAfter().isPresent()) {
            rentalBean.setPriceCalculatedAfter(priceCalculator.calculate(rental.getDepartureTime(), tmpArrivalTime, rental.getBoat(), rental.getPromotion()).get());
        }
        return rentalBean;
    }
}
