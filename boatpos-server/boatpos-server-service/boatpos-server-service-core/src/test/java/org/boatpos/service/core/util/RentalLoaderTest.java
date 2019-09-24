package org.boatpos.service.core.util;

import org.boatpos.domain.api.values.DayId;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class RentalLoaderTest extends EntityManagerProviderForBoatpos {

    @Inject
    private RentalLoader rentalLoader;

    @Test
    @Transactional
    public void testLoadOnCurrentDayBy_Ok() throws Exception {
        rentalLoader.loadOnCurrentDayBy(new DayId(1));
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testLoadOnCurrentDayBy_Exception() {
        rentalLoader.loadOnCurrentDayBy(new DayId(999));
    }

    @Test
    @Transactional
    public void testCheckIfRentalIsActive_Ok() {
        rentalLoader.checkIfRentalIsActive(new DayId(3));
        rentalLoader.checkIfRentalIsActive(new DayId(4));
    }

    @Test(expected = IllegalStateException.class)
    @Transactional
    public void testCheckIfRentalIsActive_Deleted() {
        rentalLoader.checkIfRentalIsActive(new DayId(2));
    }

    @Test(expected = IllegalStateException.class)
    @Transactional
    public void testCheckIfRentalIsActive_AlreadyFinished() {
        rentalLoader.checkIfRentalIsActive(new DayId(1));
    }

    @Test
    @Transactional
    public void testCheckIfRentalIsDeleted_Ok() {
        rentalLoader.checkIfRentalIsDeleted(new DayId(2));
    }

    @Test(expected = IllegalStateException.class)
    @Transactional
    public void testCheckIfRentalIsDeleted_NotDeleted() {
        rentalLoader.checkIfRentalIsDeleted(new DayId(1));
    }
}
