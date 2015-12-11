package org.boatpos.dao.core;

import org.boatpos.dao.api.BoatDao;
import org.boatpos.model.Boat;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class BoatDaoTest extends EntityManagerProviderForBoatpos {

    @Inject
    private BoatDao boatDao;

    @Test
    @Transactional
    public void testGetById() {
        Boat boat = boatDao.getById(1L).get();
        assertEquals("E-Boot", boat.getName());
        assertEquals(1, boat.getRentals().size());
    }

    @Test
    @Transactional
    public void testGetByName() {
        assertEquals("E", boatDao.getByName("E-Boot").get().getShortName());
    }

    @Test
    @Transactional
    public void testGetByShortName() {
        assertEquals("E-Boot", boatDao.getByShortName("E").get().getName());
    }

    @Test
    @Transactional
    public void testGetAllEnabled() {
        List<Boat> boats = boatDao.getAllEnabled();
        assertEquals(5, boats.size());
        assertEquals("E", boats.get(0).getShortName());
        assertEquals("L", boats.get(4).getShortName());
    }

    @Test
    @Transactional
    public void testGetAll() {
        assertEquals(6, boatDao.getAll().size());
    }

    @Test
    @Transactional
    public void testGetAllDisabled() {
        assertEquals(1, boatDao.getAllDisabled().size());
    }
}