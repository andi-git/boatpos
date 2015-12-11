package org.boatpos.dao.core;

import org.boatpos.dao.api.PromotionDao;
import org.boatpos.model.*;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PromotionDaoTest extends EntityManagerProviderForBoatpos {

    @Inject
    private PromotionDao promotionDao;

    @Test
    @Transactional
    public void testGetByName() {
        assertEquals(PromotionBefore.class, promotionDao.getByName("Fahr 3 zahl 2").get().getClass());
        assertEquals(PromotionAfter.class, promotionDao.getByName("HolliKnolli").get().getClass());
    }

    @Test
    @Transactional
    public void testGetAll() {
        assertEquals(5, promotionDao.getAll().size());
    }

    @Test
    @Transactional
    public void testGetAllEnabled() {
        assertEquals(3, promotionDao.getAllEnabled().size());
    }

    @Test
    @Transactional
    public void testGetAllDisabled() {
        assertEquals(2, promotionDao.getAllDisabled().size());
    }

    @Test
    @Transactional
    public void testGetAllBeforeRental() {
        assertEquals(3, promotionDao.getAllBeforeRental().size());
    }

    @Test
    @Transactional
    public void testGetAllBeforeRentalEnabled() {
        assertEquals(2, promotionDao.getAllBeforeRentalEnabled().size());
    }

    @Test
    @Transactional
    public void testGetAllBeforeRentalDisabled() {
        assertEquals(1, promotionDao.getAllBeforeRentalDisabled().size());
    }

    @Test
    @Transactional
    public void testGetAllAfterRental() {
        assertEquals(2, promotionDao.getAllAfterRental().size());
    }

    @Test
    @Transactional
    public void testGetAllAfterRentalEnabled() {
        assertEquals(1, promotionDao.getAllAfterRentalEnabled().size());
    }

    @Test
    @Transactional
    public void testGetAllAfterRentalDisabled() {
        assertEquals(1, promotionDao.getAllAfterRentalDisabled().size());
    }
}