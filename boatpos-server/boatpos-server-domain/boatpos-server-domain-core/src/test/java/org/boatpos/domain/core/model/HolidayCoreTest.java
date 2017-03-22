package org.boatpos.domain.core.model;

import org.boatpos.domain.api.model.Holiday;
import org.boatpos.domain.core.TestUtil;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class HolidayCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private TestUtil.HolidayUtil holidayUtil;

    @Test
    @Transactional
    public void testPersist() {
        holidayUtil.assertDatabaseHolidayCount(1);
        Holiday holiday = holidayUtil.createDummyHoliday().persist();
        holidayUtil.assertDatabaseHolidayCount(2);
        holiday.delete();
        holidayUtil.assertDatabaseHolidayCount(1);
    }

    @Test
    @Transactional
    public void testEntity() {
        assertEquals("Staatsfeiertag", new HolidayCore(holidayUtil.createDummyHoliday().asEntity()).getName().get());
    }

    @Test
    @Transactional
    public void testDto() {
        assertEquals("Staatsfeiertag", new HolidayCore(holidayUtil.createDummyHoliday().asDto()).getName().get());
    }
}
