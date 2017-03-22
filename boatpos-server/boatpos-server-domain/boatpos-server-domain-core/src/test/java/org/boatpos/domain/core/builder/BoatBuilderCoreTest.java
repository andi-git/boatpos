package org.boatpos.domain.core.builder;

import org.boatpos.domain.core.TestUtil;
import org.boatpos.model.BoatEntity;
import org.boatpos.domain.api.builder.BoatBuilder;
import org.boatpos.domain.api.model.Boat;
import org.boatpos.service.api.bean.BoatBean;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class BoatBuilderCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private BoatBuilder boatBuilder;

    @Inject
    private TestUtil.BoatUtil boatUtil;

    @Test
    @Transactional
    public void testBuild() {
        Boat boat = boatUtil.createDummyBoat();
        assertEquals(1L, boat.getId().get().longValue());
        assertEquals(1, boat.getVersion().get().intValue());
        assertEquals("E-Boot", boat.getName().get());
        assertEquals("E", boat.getShortName().get());
        assertEquals(new BigDecimal("16.8"), boat.getPriceOneHour().get());
        assertEquals(new BigDecimal("9.5"), boat.getPriceThirtyMinutes().get());
        assertEquals(new BigDecimal("14.3"), boat.getPriceFortyFiveMinutes().get());
        assertEquals(22, boat.getCount().get().intValue());
        assertTrue(boat.isEnabled().get());
        assertEquals(1, boat.getPriority().get().intValue());
    }

    @Test
    @Transactional
    public void testFromEntity() {
        BoatEntity boatEntity = boatUtil.getBoatByShortName("E");
        Boat boat = boatBuilder.from(boatEntity);
        assertEquals("E-Boot", boat.getName().get());
        assertEquals(8, boat.getRentals().size());
    }

    @Test
    @Transactional
    public void testAsEntity() {
        BoatEntity boatEntity = boatUtil.createDummyBoat().asEntity();
        assertEquals("E-Boot", boatEntity.getName());
    }

    @Test
    @Transactional
    @Ignore
    public void testFromDto() {
        Boat boat = boatBuilder.from(boatUtil.createDummyBoatBean());
        assertEquals("E-Boot", boat.getName().get());
    }

    @Test
    @Transactional
    public void testAsDto() {
        BoatBean boatBean = boatUtil.createDummyBoat().asDto();
        assertEquals("E-Boot", boatBean.getName());
    }
}
