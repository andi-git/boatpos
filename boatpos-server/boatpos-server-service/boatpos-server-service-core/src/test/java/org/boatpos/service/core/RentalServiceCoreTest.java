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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class RentalServiceCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private RentalService rentalService;

    @Test
    @Transactional
    public void testGet() throws Exception {
        RentalBean rentalBean = rentalService.get(new RentalDayNumberWrapper(1));
        assertEquals("E-Boot", rentalBean.getBoatBean().getName());
        assertEquals("Fahr 3 zahl 2", rentalBean.getPromotionBean().getName());
        assertEquals(new BigDecimal("1.60"), rentalBean.getPriceCalculatedAfter());
        assertEquals(new BigDecimal("32.00"), rentalBean.getPricePaidBefore());
        assertEquals(2, rentalBean.getCommitmentBeans().size());
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        assertFalse(rentalService.get(new RentalDayNumberWrapper(1)).isDeleted());
        rentalService.delete(new RentalDayNumberWrapper(1));
        assertTrue(rentalService.get(new RentalDayNumberWrapper(1)).isDeleted());
    }
}