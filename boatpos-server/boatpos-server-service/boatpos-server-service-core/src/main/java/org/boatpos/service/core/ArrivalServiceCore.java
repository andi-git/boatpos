package org.boatpos.service.core;

import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.repository.RentalRepository;
import org.boatpos.repository.api.values.ArrivalTime;
import org.boatpos.repository.api.values.Day;
import org.boatpos.repository.api.values.DayId;
import org.boatpos.repository.api.values.PriceCalculatedAfter;
import org.boatpos.service.api.ArrivalService;
import org.boatpos.service.api.bean.ArrivalBean;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.util.datetime.DateTimeHelper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ArrivalServiceCore implements ArrivalService {

    @Inject
    private RentalRepository rentalRepository;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Inject
    private PriceCalculator priceCalculator;

    @Override
    public RentalBean arrive(ArrivalBean arrivalBean) {
        Rental rental = rentalRepository.arrive(
                new Day(dateTimeHelper.currentDate()),
                new DayId(arrivalBean.getDayNumber()),
                new ArrivalTime(dateTimeHelper.currentTime()));
        PriceCalculatedAfter priceCalculatedAfter = priceCalculator.calculate(rental.getDepartureTime(), rental.getArrivalTime(), rental.getBoat(), rental.getPromotion());
        return rental.setPriceCalculatedAfter(priceCalculatedAfter).persist().asDto();
    }
}
