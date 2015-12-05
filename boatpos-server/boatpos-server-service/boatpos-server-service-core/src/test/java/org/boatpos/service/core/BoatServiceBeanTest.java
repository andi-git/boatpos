package org.boatpos.service.core;

import org.boatpos.service.api.BoatService;
import org.boatpos.service.api.bean.BoatBean;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.PersistenceException;
import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BoatServiceBeanTest extends EntityManagerProviderForBoatpos {

    @EJB
    private BoatService boatService;

    @Test
    @Transactional
    public void testGetAll() throws Exception {
        assertEquals(5, boatService.getAll().size());
    }

    @Test
    @Transactional
    public void testGetById() throws Exception {
        assertEquals("E", boatService.getById(1L).getShortName());
        assertNull(boatService.getById(999L));
    }

    @Test
    @Transactional
    public void testGetByName() throws Exception {
        assertEquals("E", boatService.getByName("E-Boot").getShortName());
        assertNull(boatService.getByName("xxx"));
    }

    @Test
    @Transactional
    public void testGetByShortName() throws Exception {
        assertEquals("E-Boot", boatService.getByShortName("E").getName());
        assertNull(boatService.getByShortName("xxx"));
    }

    @Test
    @Transactional
    public void testSave() throws Throwable {
        assertEquals(5, boatService.getAll().size());
        BoatBean boatBean = new BoatBean(null, null, "TG", "Tretboot groß", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 10, 5);
        boatService.save(boatBean);
        assertEquals(6, boatService.getAll().size());
    }

    @Test(expected = PersistenceException.class)
    @Transactional
    public void testSaveWithException() throws Throwable {
        assertEquals(5, boatService.getAll().size());
        BoatBean boatBean = new BoatBean(-1L, null, "xxxx", "Tretboot groß", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 10, 5);
        try {
            boatService.save(boatBean);
        } catch (Exception e) {
            throw e.getCause();
        }
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        BoatBean boatBean = boatService.getByShortName("E");
        boatBean.setName("EBOOT");
        boatService.update(boatBean);
        assertEquals(2, boatService.getByName("EBOOT").getVersion().intValue());
        assertEquals(5, boatService.getAll().size());
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        assertEquals(5, boatService.getAll().size());
        boatService.delete(1L);
        assertEquals(4, boatService.getAll().size());
    }
}
