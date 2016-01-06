package org.boatpos.service.api.bean;

import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RentalDayNumberWrapperTest extends JavaBeanTest<RentalDayNumberWrapper> {

    @Override
    protected Class<RentalDayNumberWrapper> getType() {
        return RentalDayNumberWrapper.class;
    }

    @Test
    public void testConstructor() {
        new RentalDayNumberWrapper(1);
    }

    @Test
    public void testEqualsAndHashCode() {
        RentalDayNumberWrapper rentalDayNumberWrapper1 = new RentalDayNumberWrapper(1);
        RentalDayNumberWrapper rentalDayNumberWrapper2 = new RentalDayNumberWrapper(2);
        assertEquals(rentalDayNumberWrapper1, rentalDayNumberWrapper1);
        assertEquals(rentalDayNumberWrapper2, rentalDayNumberWrapper2);
        assertNotEquals(rentalDayNumberWrapper1, rentalDayNumberWrapper2);
        assertEquals(rentalDayNumberWrapper1.hashCode(), rentalDayNumberWrapper1.hashCode());
        assertEquals(rentalDayNumberWrapper2.hashCode(), rentalDayNumberWrapper2.hashCode());
        assertNotEquals(rentalDayNumberWrapper1.hashCode(), rentalDayNumberWrapper2.hashCode());
    }
}