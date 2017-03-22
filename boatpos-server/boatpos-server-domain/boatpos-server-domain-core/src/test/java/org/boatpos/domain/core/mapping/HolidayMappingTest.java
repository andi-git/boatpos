package org.boatpos.domain.core.mapping;

import org.boatpos.domain.core.TestUtil;
import org.boatpos.model.HolidayEntity;
import org.boatpos.service.api.bean.HolidayBean;
import org.boatpos.common.util.datetime.DateTimeHelper;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class HolidayMappingTest {

    @Inject
    private HolidayMapping holidayMapping;

    @Inject
    private TestUtil.HolidayUtil holidayUtil;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Test
    public void testHolidayToHolidayBean() {
        HolidayEntity holidayEntity = holidayUtil.createDummyHoliday().asEntity();
        HolidayBean holidayBean = holidayMapping.mapEntity(holidayEntity);
        assertEquals(LocalDate.of(2015, 5, 1), holidayBean.getDay());
        assertEquals("Staatsfeiertag", holidayBean.getName());
    }

    @Test
    public void testHolidayBeanToHoliday() {
        HolidayBean holidayBean = holidayUtil.createDummyHolidayBean();
        HolidayEntity holidayEntity = holidayMapping.mapDto(holidayBean);
        assertEquals(LocalDate.of(2015, 5, 1), holidayEntity.getDay());
        assertEquals("Staatsfeiertag", holidayEntity.getName());
    }
}