package org.boatpos.common.util.datetime;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class DateTimeHelperTest {

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Test
    public void testCurrentDate() throws Exception {
        assertNotNull(dateTimeHelper.currentDate());
    }

    @Test
    public void testCurrentTime() throws Exception {
        assertNotNull(dateTimeHelper.currentTime());
    }
}