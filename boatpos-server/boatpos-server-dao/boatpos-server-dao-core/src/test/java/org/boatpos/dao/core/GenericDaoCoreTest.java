package org.boatpos.dao.core;

import org.boatpos.model.Boat;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class GenericDaoCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private DaoToTestGeneric dao;

    @Before
    public void before() {
        assertBoatCount(6);
    }

    @Test
    @Transactional
    public void testGetById() throws Exception {
        assertEquals("E-Boot", dao.getById(1L).get().getName());
        assertEquals("Tretboot klein", dao.getById(5L).get().getName());
        assertBoatCount(6);
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        Boat boat = new Boat(null, 1, "Tretboot groß", "T4", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 10, 0, new HashSet<>(), true);
        dao.save(boat);
        assertEquals("Tretboot groß", dao.getById(boat.getId()).get().getName());
        assertBoatCount(7);
    }

    @Test
    @Transactional
    public void testSaveWithException() throws Exception {
        try {
            Boat boat = new Boat(null, 1, "", "T4", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 10, 0, new HashSet<>(), true);
            dao.save(boat);
            fail("exception must be thrown");
        } catch (PersistenceException e) {
            assertTrue(e.getMessage().contains("exception on save entity"));
        }
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        Boat boat = dao.getById(1L).get();
        boat.setName("Elektroboot");
        dao.update(boat);
        assertEquals("Elektroboot", dao.getById(1L).get().getName());
        assertBoatCount(6);
    }

    @Test
    @Transactional
    public void testUpdateException() throws Exception {
        Boat boat = dao.getById(1L).get();
        boat.setName(null);
        try {
            dao.update(boat);
            fail("exception must be thrown");
        } catch (PersistenceException e) {
            assertTrue(e.getMessage().contains("exception on update entity"));
        }
    }

    @Test
    @Transactional
    public void testDeleteById() throws Exception {
        dao.delete(1L);
        assertBoatCount(6);
    }

    @Test
    @Transactional
    public void testDeleteByIdWrongId() throws Exception {
        try {
            dao.delete(3L);
            fail("exception must be thrown");
        } catch (PersistenceException e) {
            assertTrue(e.getMessage().contains("no entity with id"));
        }
    }

    @Test
    @Transactional
    public void testDeleteByEntity() throws Exception {
        dao.delete(dao.getById(1L).get());
        assertBoatCount(6);
    }


    @Test
    @Transactional
    public void testDeleteByEntityDetached() throws Exception {
        try {
            Boat boat = new Boat(3L, 1, "", "T4", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 10, 0, new HashSet<>(), true);
            dao.delete(boat);
            fail("exception must be thrown");
        } catch (PersistenceException e) {
            assertTrue(e.getMessage().contains("exception on delete entity"));
        }
    }

    @Test(expected = NonUniqueResultException.class)
    @Transactional
    public void testGetSingleResult() {
        assertFalse(dao.getSingleResult(new ArrayList<>()).isPresent());
        assertTrue(dao.getSingleResult(Collections.singletonList(new Boat())).isPresent());
        dao.getSingleResult(Arrays.asList(new Boat(), new Boat()));

    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional
    public void createNamedQueryTest() {
        dao.createTypedNamedQuery("test");
    }

    private void assertBoatCount(int count) {
        //noinspection SqlDialectInspection,SqlNoDataSourceInspection
        assertEquals(BigInteger.valueOf(count), getEntityManager().createNativeQuery("SELECT COUNT(*) FROM boat").getSingleResult());
    }
}
