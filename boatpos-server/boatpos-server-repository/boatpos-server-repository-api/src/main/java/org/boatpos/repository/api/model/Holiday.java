package org.boatpos.repository.api.model;

import org.boatpos.model.HolidayEntity;
import org.boatpos.model.PaymentMethod;
import org.boatpos.model.RentalEntity;
import org.boatpos.repository.api.values.*;
import org.boatpos.service.api.bean.HolidayBean;
import org.boatpos.service.api.bean.RentalBean;

import java.util.Optional;
import java.util.Set;

/**
 * The domain model for a holiday.
 */
public interface Holiday extends DomainModel<Holiday, HolidayEntity, HolidayBean> {

    Day getDay();

    Holiday setDay(Day day);

    Name getName();

    Holiday setName(Name name);
}
