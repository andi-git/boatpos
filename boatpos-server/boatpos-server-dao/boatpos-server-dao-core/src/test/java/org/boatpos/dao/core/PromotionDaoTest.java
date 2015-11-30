package org.boatpos.dao.core;

import org.boatpos.dao.api.PromotionDao;
import org.boatpos.dao.api.RentalDao;
import org.boatpos.model.Promotion;
import org.boatpos.model.Rental;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PromotionDaoTest extends EntityManagerProviderForBoatpos {

    @Inject
    private PromotionDao promotionDao;

    @Test
    @Transactional
    public void testGetById() {
        Promotion promotion = promotionDao.getById(3L).get();
        assertEquals("Fahr 3 zahl 2", promotion.getName());
    }
}