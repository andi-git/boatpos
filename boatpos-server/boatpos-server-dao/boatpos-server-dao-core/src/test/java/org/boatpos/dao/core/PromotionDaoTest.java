package org.boatpos.dao.core;

import org.boatpos.dao.api.PromotionDao;
import org.boatpos.dao.api.RentalDao;
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
        List<Promotion> promotions = promotionDao.getAll();
        assertEquals(3, promotions.size());
    }

    @Test
    @Transactional
    public void testGetAllBeforeRental() {
        List<PromotionBefore> promotions = promotionDao.getAllBeforeRental();
        assertEquals(2, promotions.size());
    }

    @Test
    @Transactional
    public void testGetAllAfterRental() {
        List<PromotionAfter> promotions = promotionDao.getAllAfterRental();
        assertEquals(1, promotions.size());
    }
}