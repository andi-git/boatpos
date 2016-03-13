package org.boatpos.repository.core.model;

import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.common.repository.api.values.Version;
import org.boatpos.common.repository.core.model.DomainModelCore;
import org.boatpos.model.HolidayEntity;
import org.boatpos.repository.api.model.Holiday;
import org.boatpos.repository.api.values.*;
import org.boatpos.repository.core.mapping.HolidayMapping;
import org.boatpos.service.api.bean.HolidayBean;

import static com.google.common.base.Preconditions.checkNotNull;

public class HolidayCore extends DomainModelCore<Holiday, HolidayEntity, HolidayBean> implements Holiday {

    public HolidayCore(DomainId id,
                       Version version,
                       Day day,
                       Name name) {
        super(id, version);
        checkNotNull(day, "'day' must not be null");
        setDay(day);
        setName(name);
    }

    public HolidayCore(HolidayEntity holidayEntity) {
        super(holidayEntity);
    }

    public HolidayCore(HolidayBean holidayBean) {
        this(HolidayMapping.fromCDI().mapDto(holidayBean));
    }

    @Override
    public Day getDay() {
        return new Day(getEntity().getDay());
    }

    @Override
    public Holiday setDay(Day day) {
        getEntity().setDay(SimpleValueObject.nullSafe(day));
        return this;

    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    @Override
    public Holiday setName(Name name) {
        getEntity().setName(SimpleValueObject.nullSafe(name));
        return this;
    }

    @Override
    public HolidayBean asDto() {
        return HolidayMapping.fromCDI().mapEntity(getEntity());
    }
}