package org.boatpos.service.core.mapping;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.boatpos.model.Boat;
import org.boatpos.service.api.bean.BoatBean;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class BoatMappingTest {

    @Inject
    private BoatMapping boatMapping;

    @Test
    public void testBoatToBoatBean() {
        Boat boat = new Boat(1L, 1, "E-Boot", "E", new BigDecimal("16.8"), new BigDecimal("9.5"), new BigDecimal("14.3"), 22, 1, new HashSet<>(), true);
        assertEquals(1L, boatMapping.mapEntity(boat).getId().longValue());
        assertEquals(1L, boatMapping.mapEntity(Optional.of(boat)).get().getId().longValue());
        assertFalse(boatMapping.mapEntity(Optional.empty()).isPresent());
    }

    @Test
    public void testBoatBeanToBoat() {
        BoatBean boatBean = new BoatBean(1L, 1, "E-Boot", "E", new BigDecimal("16.8"), new BigDecimal("9.5"), new BigDecimal("14.3"), 22, 1, true);
        assertEquals(1L, boatMapping.mapDto(boatBean).getId().longValue());
        assertEquals(1L, boatMapping.mapDto(Optional.of(boatBean)).get().getId().longValue());
        assertFalse(boatMapping.mapDto(Optional.empty()).isPresent());
    }

    @Test
    public void testBoatCollectionToBoatBeanCollection() {
        Boat boat1 = new Boat(1L, 1, "E-Boot", "E", new BigDecimal("16.8"), new BigDecimal("9.5"), new BigDecimal("14.3"), 22, 1, new HashSet<>(), true);
        Boat boat2 = new Boat(2L, 1, "E-Boot", "E", new BigDecimal("16.8"), new BigDecimal("9.5"), new BigDecimal("14.3"), 22, 1, new HashSet<>(), true);

        List<BoatBean> boatList = boatMapping.mapEntities(Lists.newArrayList(boat1, boat2));
        assertEquals(2, boatList.size());
        assertEquals(1L, boatList.get(0).getId().longValue());
        assertEquals(2L, boatList.get(1).getId().longValue());

        Set<BoatBean> boatSet = boatMapping.mapEntities(Sets.newHashSet(boat1, boat2));
        assertEquals(2, boatSet.size());
        boatSet.stream().forEach(b -> assertNotNull(b.getId()));
    }

    @Test
    public void testBoatBeanCollectionToBoatCollection() {
        BoatBean boatBean1 = new BoatBean(1L, 1, "E-Boot", "E", new BigDecimal("16.8"), new BigDecimal("9.5"), new BigDecimal("14.3"), 22, 1, true);
        BoatBean boatBean2 = new BoatBean(2L, 1, "E-Boot", "E", new BigDecimal("16.8"), new BigDecimal("9.5"), new BigDecimal("14.3"), 22, 1, true);

        List<Boat> boatList = boatMapping.mapDtos(Lists.newArrayList(boatBean1, boatBean2));
        assertEquals(2, boatList.size());
        assertEquals(1L, boatList.get(0).getId().longValue());
        assertEquals(2L, boatList.get(1).getId().longValue());

        Set<Boat> boatSet = boatMapping.mapDtos(Sets.newHashSet(boatBean1, boatBean2));
        assertEquals(2, boatSet.size());
        boatSet.stream().forEach(b -> assertNotNull(b.getId()));
    }
}