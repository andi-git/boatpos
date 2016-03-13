package org.boatpos.repository.api.builder;

import org.boatpos.common.repository.api.builder.DomainModelBuilder;
import org.boatpos.model.HolidayEntity;
import org.boatpos.repository.api.model.Holiday;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.values.Day;
import org.boatpos.repository.api.values.Name;
import org.boatpos.service.api.bean.HolidayBean;

/**
 * Builder for {@link Rental}.
 */
public interface HolidayBuilder extends DomainModelBuilder<HolidayBuilder, Holiday, HolidayEntity, HolidayBean> {

    HolidayBuilder add(Day day);

    HolidayBuilder add(Name name);
}
