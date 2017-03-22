package org.boatpos.domain.core.mapping;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Version;
import org.boatpos.model.BoatEntity;
import org.boatpos.domain.api.repository.BoatRepository;
import org.boatpos.domain.core.TestUtil;
import org.boatpos.service.api.bean.BoatBean;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BoatMappingTest {

    @Inject
    private BoatMapping boatMapping;

    @Inject
    private BoatRepository boatRepository;

    @Inject
    private TestUtil.BoatUtil boatUtil;

    @Test
    public void testBoatToBoatBean() {
        BoatEntity boat = boatUtil.createDummyBoatBuilder().build().asEntity();
        assertEquals(1L, boatMapping.mapEntity(boat).getId().longValue());
        assertEquals(1L, boatMapping.mapEntity(Optional.of(boat)).get().getId().longValue());
        assertFalse(boatMapping.mapEntity(Optional.empty()).isPresent());
    }

    @Test
    public void testBoatBeanToBoat() {
        BoatBean boatBean = boatUtil.createDummyBoatBuilder().build().asDto();
        assertEquals(1L, boatMapping.mapDto(boatBean).getId().longValue());
        assertEquals(1L, boatMapping.mapDto(Optional.of(boatBean)).get().getId().longValue());
        assertFalse(boatMapping.mapDto(Optional.empty()).isPresent());
    }

    @Test
    public void testBoatCollectionToBoatBeanCollection() {
        BoatEntity boat1 = boatUtil.createDummyBoatBuilder().add(new DomainId(1L)).add(new Version(1)).build().asEntity();
        BoatEntity boat2 = boatUtil.createDummyBoatBuilder().add(new DomainId(2L)).add(new Version(1)).build().asEntity();

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
        BoatBean boatBean1 = boatUtil.createDummyBoatBuilder().add(new DomainId(1L)).add(new Version(1)).build().asDto();
        BoatBean boatBean2 = boatUtil.createDummyBoatBuilder().add(new DomainId(2L)).add(new Version(1)).build().asDto();

        List<BoatEntity> boatList = boatMapping.mapDtos(Lists.newArrayList(boatBean1, boatBean2));
        assertEquals(2, boatList.size());
        assertEquals(1L, boatList.get(0).getId().longValue());
        assertEquals(2L, boatList.get(1).getId().longValue());

        Set<BoatEntity> boatSet = boatMapping.mapDtos(Sets.newHashSet(boatBean1, boatBean2));
        assertEquals(2, boatSet.size());
        boatSet.stream().forEach(b -> assertNotNull(b.getId()));
    }
}