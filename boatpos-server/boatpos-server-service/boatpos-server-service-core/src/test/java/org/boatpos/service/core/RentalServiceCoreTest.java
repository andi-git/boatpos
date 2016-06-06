package org.boatpos.service.core;

import org.boatpos.service.api.RentalService;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.service.api.bean.RentalDayNumberWrapper;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class RentalServiceCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private RentalService rentalService;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Test
    @Transactional
    public void testGet() throws Exception {
        RentalBean rentalBean = rentalService.get(new RentalDayNumberWrapper(1));
        assertEquals("E-Boot", rentalBean.getBoatBean().getName());
        assertEquals("Fahr 3 zahl 2", rentalBean.getPromotionBeforeBean().getName());
        assertEquals(new BigDecimal("1.60"), rentalBean.getPriceCalculatedAfter());
        assertEquals(new BigDecimal("32.00"), rentalBean.getPricePaidBefore());
        assertEquals(2, rentalBean.getCommitmentBeans().size());
        assertEquals(130, rentalBean.getTimeOfTravel().intValue());
        assertEquals(125, rentalBean.getTimeOfTravelCalculated().intValue());
        assertEquals(new BigDecimal("1.60"), rentalBean.getPriceCalculatedAfter());

        rentalBean = rentalService.get(new RentalDayNumberWrapper(2));
        assertEquals(new BigDecimal("34.40"), rentalBean.getPriceCalculatedAfter());
        assertEquals(166, rentalBean.getTimeOfTravel().intValue());
        assertEquals(161, rentalBean.getTimeOfTravelCalculated().intValue());
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        assertFalse(rentalService.get(new RentalDayNumberWrapper(1)).isDeleted());
        rentalService.delete(new RentalDayNumberWrapper(1));
        assertTrue(rentalService.get(new RentalDayNumberWrapper(1)).isDeleted());
    }

    @Test
    @Transactional
    public void testUndoDelete() throws Exception {
        assertTrue(rentalService.get(new RentalDayNumberWrapper(2)).isDeleted());
        rentalService.undoDelete(new RentalDayNumberWrapper(2));
        assertFalse(rentalService.get(new RentalDayNumberWrapper(2)).isDeleted());
    }

    @Test
    @Transactional
    public void testNextDayId() {
        assertEquals(6, rentalService.nextDayId().getDayNumber().intValue());
    }

    @Test
    @Transactional
    public void testGetAllForCurrentDay() {
        List<RentalBean> rentalBeans = rentalService.getAllCurrentDay();
        assertEquals(5, rentalBeans.size());
        assertEquals(1, rentalBeans.get(0).getDayId().intValue());
        assertEquals(130, rentalBeans.get(0).getTimeOfTravel().intValue());
    }

    @Test
    @Transactional
    public void testGetAllForDate() {
        List<RentalBean> rentalBeans = rentalService.getAll(dateTimeHelper.currentDate());
        assertEquals(5, rentalBeans.size());
        assertEquals(1, rentalBeans.get(0).getDayId().intValue());
        assertEquals(130, rentalBeans.get(0).getTimeOfTravel().intValue());
    }

    @Test
    @Transactional
    public void testGetGetByDateAndDayId() {
        RentalBean rentalBean = rentalService.get(dateTimeHelper.currentDate(), new RentalDayNumberWrapper(1));
        assertEquals("E", rentalBean.getBoatBean().getShortName());
        assertEquals("GwLtkrFX3ehALyQoR1I8bg%3D%3D", rentalBean.getMyRentalId());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testGetGetByDateAndDayIdException() {
        rentalService.get(dateTimeHelper.currentDate(), new RentalDayNumberWrapper(999));
    }
}
