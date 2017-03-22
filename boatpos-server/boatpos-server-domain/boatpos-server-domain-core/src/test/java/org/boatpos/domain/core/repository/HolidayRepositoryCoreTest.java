package org.boatpos.domain.core.repository;

import org.boatpos.domain.api.repository.HolidayRepository;
import org.boatpos.domain.api.values.Day;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Arquillian.class)
public class HolidayRepositoryCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private HolidayRepository holidayRepository;

    @Test
    @Transactional
    public void testLoadByDay() {
        assertEquals("Mariä Himmelfahrt", holidayRepository.loadBy(new Day(2015, 8, 15)).get().getName().get());
        assertFalse(holidayRepository.loadBy(new Day(2015, 4, 14)).isPresent());
    }
}
