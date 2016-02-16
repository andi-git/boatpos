package org.boatpos.repository.core.builder;

import org.boatpos.model.HolidayEntity;
import org.boatpos.model.PaymentMethod;
import org.boatpos.model.RentalEntity;
import org.boatpos.repository.api.builder.HolidayBuilder;
import org.boatpos.repository.api.builder.RentalBuilder;
import org.boatpos.repository.api.model.*;
import org.boatpos.repository.api.values.*;
import org.boatpos.repository.core.model.HolidayCore;
import org.boatpos.repository.core.model.RentalCore;
import org.boatpos.service.api.bean.HolidayBean;
import org.boatpos.service.api.bean.RentalBean;

import javax.enterprise.context.Dependent;
import java.util.HashSet;
import java.util.Set;

@Dependent
public class HolidayBuilderCore extends DomainModelBuilderCore<HolidayBuilder, Holiday, HolidayCore, HolidayEntity, HolidayBean> implements HolidayBuilder {

    protected Day day;
    protected Name name;

    @Override
    public HolidayBuilder add(Day day) {
        this.day = day;
        return this;
    }

    @Override
    public HolidayBuilder add(Name name) {
        this.name = name;
        return this;
    }

    @Override
    public Holiday build() {
        return new HolidayCore(id, version, day, name);
    }
}
