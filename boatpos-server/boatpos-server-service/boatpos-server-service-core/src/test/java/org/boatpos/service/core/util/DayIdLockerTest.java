package org.boatpos.service.core.util;

import org.boatpos.domain.api.values.DayId;
import org.boatpos.service.core.DateTimeHelperMock;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class DayIdLockerTest {

    @Inject
    private DayIdLocker dayIdLocker;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Test
    public void testLockAndRemove() {
        dayIdLocker.lock(new DayId(1));
        dayIdLocker.lock(new DayId(2));
        dayIdLocker.lock(new DayId(3));
        assertTrue(dayIdLocker.isLocked(new DayId(1)));
        assertFalse(dayIdLocker.isLocked(new DayId(4)));
        dayIdLocker.release(new DayId(1));
        assertFalse(dayIdLocker.isLocked(new DayId(1)));
    }

    @Test
    public void testReleaseByTime() {
        LocalDateTime now = LocalDateTime.now();
        dateTimeHelper.setTime(now);
        dayIdLocker.lock(new DayId(1));
        dayIdLocker.lock(new DayId(2));
        dayIdLocker.lock(new DayId(3));
        assertTrue(dayIdLocker.isLocked(new DayId(1)));
        dateTimeHelper.setTime(now.plusMinutes(1));
        assertTrue(dayIdLocker.isLocked(new DayId(1)));
        dateTimeHelper.setTime(now.plusMinutes(2));
        dayIdLocker.releaseByTime();
        assertFalse(dayIdLocker.isLocked(new DayId(1)));
        assertFalse(dayIdLocker.isLocked(new DayId(2)));
        assertFalse(dayIdLocker.isLocked(new DayId(3)));
    }
}
