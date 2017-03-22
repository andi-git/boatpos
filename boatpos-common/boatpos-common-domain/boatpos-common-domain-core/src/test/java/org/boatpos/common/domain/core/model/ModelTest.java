package org.boatpos.common.domain.core.model;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.domain.api.values.Version;
import org.boatpos.common.domain.core.boat.Boat;
import org.boatpos.common.domain.core.boat.Price;
import org.boatpos.common.domain.core.boat.BoatCore;
import org.boatpos.common.domain.core.boat.Name;
import org.boatpos.common.test.EntityManagerProvider;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ModelTest implements EntityManagerProvider {

    @PersistenceContext(unitName = "my-persistence")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Test
    @Transactional
    public void testConstructor() throws Exception {
        BoatCore.createDummyWithoutId();
    }

    @Test
    @Transactional
    public void testGetterAndSetter() {
        BoatCore boatCore = BoatCore.createDummyWithoutId();
        boatCore.persist();
        assertTrue(boatCore.getId().get() > 0);
        assertEquals(0, boatCore.getVersion().get().intValue());
        assertFalse(boatCore.setEnabled(Enabled.FALSE).isEnabled().get());
        assertEquals(99, boatCore.setPriority(new Priority(99)).getPriority().get().intValue());
        assertEquals('y', boatCore.setKeyBinding(new KeyBinding('y')).getKeyBinding().get().charValue());
        assertEquals(new BigDecimal("11.1"), boatCore.setPrice(new Price("11.1")).getPrice().get());
        assertEquals("p", boatCore.setPictureUrl(new PictureUrl("p")).getPictureUrl().get());
        assertEquals("t", boatCore.setPictureUrlThumb(new PictureUrlThumb("t")).getPictureUrlThumb().get());
        assertFalse(boatCore.disable().isEnabled().get());
        assertTrue(boatCore.enable().isEnabled().get());
    }

    @Test
    @Transactional
    public void testEqualsHashCodeToString() {
        assertEquals(BoatCore.createDummyWithId1(), BoatCore.createDummyWithId3());
        assertNotEquals(BoatCore.createDummyWithId1(), BoatCore.createDummyWithId2());
        assertNotEquals(BoatCore.createDummyWithId2(), BoatCore.createDummyWithId3());

        assertEquals(BoatCore.createDummyWithId1().hashCode(), BoatCore.createDummyWithId3().hashCode());
        assertNotEquals(BoatCore.createDummyWithId1().hashCode(), BoatCore.createDummyWithId2().hashCode());
        assertNotEquals(BoatCore.createDummyWithId2().hashCode(), BoatCore.createDummyWithId3().hashCode());

        assertEquals("{\"name\":\"aName\",\"price\":9.50,\"enabled\":true,\"priority\":5,\"keyBinding\":\"x\",\"pictureUrlThumb\":\"thumb\",\"pictureUrl\":\"pic\",\"id\":99,\"version\":0}", BoatCore.createDummyWithId1().toString());
    }

    @Test
    @Transactional
    public void testDelete() {
        Boat boat = BoatCore.createDummyWithoutId().persist();
        boat.delete();
        assertFalse(boat.isEnabled().get());

        Foo foo1 = new FooCore(new DomainId(50L), new Version(0)).persist();
        new FooCore(new DomainId(51L), new Version(0)).persist();
        new FooCore(new DomainId(52L), new Version(0)).persist();
        foo1.delete();
    }

    @Test
    @Transactional
    public void testPersistException() {
        try {
            BoatCore.createDummyWithoutId().setName(new Name("x")).persist();
            fail("constraints are not ok");
        } catch (Exception e) {
            //ok
        }
    }

    @Test
    @Transactional
    public void testAsDtoAsEntity() {
        assertEquals("aName", BoatCore.createDummyWithoutId().asDto().getName());
        assertEquals("aName", BoatCore.createDummyWithoutId().asEntity().getName());
    }
}
