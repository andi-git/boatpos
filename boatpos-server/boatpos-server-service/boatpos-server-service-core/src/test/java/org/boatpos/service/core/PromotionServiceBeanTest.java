package org.boatpos.service.core;

import org.boatpos.service.api.PromotionService;
import org.boatpos.service.api.bean.PromotionBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class PromotionServiceBeanTest extends EntityManagerProviderForBoatpos {

    @EJB
    private PromotionService promotionService;

    @Test
    @Transactional
    public void testGetAll() throws Exception {
        assertEquals(3, promotionService.getAll().size());
    }

    @Test
    @Transactional
    public void testGetAllBeforeRental() throws Exception {
        assertEquals(2, promotionService.getAllBeforeRental().size());
    }

    @Test
    @Transactional
    public void testGetAllAfterRental() throws Exception {
        assertEquals(1, promotionService.getAllAfterRental().size());

    }

    @Test
    @Transactional
    public void testGetById() throws Exception {
        assertEquals("Fahr 3 zahl 2", promotionService.getById(3L).getName());
    }

    @Test
    @Transactional
    public void testGetByName() throws Exception {
        assertEquals(3L, promotionService.getByName("Fahr 3 zahl 2").getId().longValue());
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        promotionService.save(new PromotionBeforeBean(null, 1, "PROMO", 300, "price / 3", 3));
        assertEquals(4, promotionService.getAll().size());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        PromotionBean promotionBean = promotionService.getByName("Fahr 3 zahl 2");
        promotionBean.setName("F3Z2");
        promotionService.update(promotionBean);
        assertEquals(2, promotionService.getByName("F3Z2").getVersion().intValue());
        assertEquals(3, promotionService.getAll().size());
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        assertEquals(3, promotionService.getAll().size());
        promotionService.delete(3L);
        assertEquals(2, promotionService.getAll().size());
    }
}