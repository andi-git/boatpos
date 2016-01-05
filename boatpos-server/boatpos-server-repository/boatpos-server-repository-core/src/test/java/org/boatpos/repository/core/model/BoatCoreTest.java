package org.boatpos.repository.core.model;

import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.repository.BoatRepository;
import org.boatpos.repository.api.values.*;
import org.boatpos.repository.core.TestUtil;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BoatCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private BoatRepository boatRepository;

    @Inject
    private TestUtil.BoatUtil boatUtil;

    @Test
    @Transactional
    public void testEntity() {
        assertEquals("E-Boot", new BoatCore(boatUtil.createDummyBoat().asEntity()).getName().get());
    }

    @Test
    @Transactional
    public void testDto() {
        assertEquals("E-Boot", boatRepository.loadBy(new ShortName("E")).get().getName().get());
    }

    @Test
    @Transactional
    public void testPersist() {
        boatUtil.assertDatabaseBoatCount(6);
        Boat boat = boatUtil.createDummyBoat().setId(new DomainId(null)).setVersion(new Version(null));
        boat.persist();
        boatUtil.assertDatabaseBoatCount(7);
    }

    @Test
    @Transactional
    public void testPersistNotValid() {
        boatUtil.assertDatabaseBoatCount(6);
        Boat boat = boatUtil.createDummyBoat().setId(new DomainId(null)).setVersion(new Version(null)).setName(new Name("x"));
        try {
            boat.persist();
            fail("Exception must be thrown!");
        } catch (Exception e) {
            // ok
        }
    }

    @Test
    @Transactional
    public void testUpdate() {
        boatUtil.assertDatabaseBoatCount(6);
        Boat boat = boatRepository.builder().from(boatUtil.getBoatByShortName("E"));
        assertEquals("E-Boot", boat.getName().get());
        boat.setName(new Name("EBOOT"));
        boat.persist();
        assertEquals("EBOOT", boatUtil.getBoatByShortName("E").getName());
        boatUtil.assertDatabaseBoatCount(6);
    }

    @Test
    @Transactional
    public void testUpdateException() {
        boatUtil.assertDatabaseBoatCount(6);
        Boat boat = boatRepository.builder().from(boatUtil.getBoatByShortName("E"));
        assertEquals("E-Boot", boat.getName().get());
        boat.setName(new Name("x"));
        try {
            boat.persist();
            fail("Exception must be thrown!");
        } catch (Exception e) {
            // ok
        }
    }

    @Test
    @Transactional
    public void testDelete() {
        boatUtil.assertDatabaseBoatCount(6);
        Boat boat = boatRepository.builder().from(boatUtil.getBoatByShortName("E"));
        boat.delete();
        assertFalse(boatUtil.getBoatByShortName("E").isEnabled());
        boatUtil.assertDatabaseBoatCount(6);
    }

    @Test
    @Transactional
    public void testDisable() {
        assertEquals(5, boatRepository.loadAll(Enabled.TRUE).size());
        boatRepository.loadBy(new ShortName("E")).get().disable();
        assertEquals(4, boatRepository.loadAll(Enabled.TRUE).size());
    }

    @Test
    @Transactional
    public void testEnable() {
        assertEquals(5, boatRepository.loadAll(Enabled.TRUE).size());
        boatRepository.loadBy(new ShortName("P")).get().enable();
        assertEquals(6, boatRepository.loadAll(Enabled.TRUE).size());
    }

    @Test
    @Transactional
    public void testEquals() {
        Boat boat1 = boatUtil.createDummyBoat();
        Boat boat2 = boatUtil.createDummyBoat();
        Boat boat3 = boatUtil.createDummyBoat().setId(new DomainId(2L));

        assertEquals(boat1, boat2);
        assertNotEquals(boat1, boat3);

        boat1 = boatUtil.createDummyBoatBuilder().add(new DomainId(null)).build();
        boat2 = boatUtil.createDummyBoatBuilder().add(new DomainId(null)).build();
        assertNotEquals(boat1, boat2);
    }

    @Test
    @Transactional
    public void testHashCode() {
        Boat boat1 = boatUtil.createDummyBoat();
        Boat boat2 = boatUtil.createDummyBoat();
        Boat boat3 = boatUtil.createDummyBoat().setId(new DomainId(2L));

        assertEquals(boat1.hashCode(), boat2.hashCode());
        assertNotEquals(boat1.hashCode(), boat3.hashCode());

        boat1.setId(null);
        boat2.setId(null);
        assertNotEquals(boat1.hashCode(), boat2.hashCode());
    }

    @Test
    @Transactional
    public void testToString() {
        assertEquals("{\"name\":\"E-Boot\",\"shortName\":\"E\",\"priceOneHour\":16.8,\"priceThirtyMinutes\":9.5,\"priceFortyFiveMinutes\":14.3,\"count\":22,\"pictureUrlSmall\":\"small_____\",\"pictureUrlMedium\":\"medium____\",\"pictureUrlLarge\":\"large_____\",\"enabled\":true,\"priority\":1,\"id\":1,\"version\":1}", boatUtil.createDummyBoat().toString());
    }
}
