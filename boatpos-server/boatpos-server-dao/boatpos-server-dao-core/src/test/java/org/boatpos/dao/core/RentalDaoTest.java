package org.boatpos.dao.core;

import org.boatpos.dao.api.BoatDao;
import org.boatpos.dao.api.RentalDao;
import org.boatpos.model.Boat;
import org.boatpos.model.Rental;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class RentalDaoTest extends EntityManagerProviderForBoatpos {

    @Inject
    private RentalDao rentalDao;

    @Test
    @Transactional
    public void testGetById() {
        Rental rental = rentalDao.getById(2L).get();
        assertEquals("E-Boot", rental.getBoat().getName());
    }
}