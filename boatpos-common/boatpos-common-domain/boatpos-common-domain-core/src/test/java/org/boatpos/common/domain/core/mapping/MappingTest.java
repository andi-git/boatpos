package org.boatpos.common.domain.core.mapping;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.boatpos.common.domain.core.boat.BoatEntity;
import org.boatpos.common.domain.core.boat.BoatMapping;
import org.boatpos.common.domain.core.boat.BoatBean;
import org.boatpos.common.test.EntityManagerProvider;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class MappingTest implements EntityManagerProvider {

    @PersistenceContext(unitName = "my-persistence")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Inject
    private BoatMapping boatMapping;

    @Test
    @Transactional
    public void testMapEntityToNewDto() throws Exception {
        assertEquals("dummy", boatMapping.mapEntity(BoatEntity.createDummyWithoutId1()).getName());
    }

    @Test
    @Transactional
    public void testMapEntityOptional() throws Exception {
        assertFalse(boatMapping.mapEntity(Optional.empty()).isPresent());
        assertTrue(boatMapping.mapEntity(Optional.of(BoatEntity.createDummyWithoutId1())).isPresent());
    }

    @Test
    @Transactional
    public void testMapEntityToExistingDto() throws Exception {
        BoatBean boatBean = new BoatBean();
        boatMapping.mapEntity(BoatEntity.createDummyWithoutId1(), boatBean);
        assertEquals("dummy", boatBean.getName());
    }

    @Test
    @Transactional
    public void testMapDtoToNewEntity() throws Exception {
        assertEquals("dummy", boatMapping.mapDto(BoatBean.createDummyWithoutId1()).getName());
        assertEquals("dummy", boatMapping.mapDto(BoatBean.createDummyWithIdNotExisting1()).getName());
        assertEquals("dummy", boatMapping.mapDto(BoatBean.createDummyWithIdExisting()).getName());
    }

    @Test
    @Transactional
    public void testMapDtoOptional() throws Exception {
        assertFalse(boatMapping.mapDto(Optional.empty()).isPresent());
        assertTrue(boatMapping.mapDto(Optional.of(BoatBean.createDummyWithoutId1())).isPresent());
    }

    @Test
    @Transactional
    public void testMapDtoToExistingEntity() throws Exception {
        BoatEntity boatEntity = new BoatEntity();
        boatMapping.mapDto(BoatBean.createDummyWithoutId1(), boatEntity);
        assertEquals("dummy", boatEntity.getName());
    }

    @Test
    @Transactional
    public void testGetMappedDtoClass() throws Exception {
        Assert.assertEquals(BoatBean.class, boatMapping.getMappedDtoClass());
    }

    @Test
    @Transactional
    public void testGetMappedEntityClass() throws Exception {
        Assert.assertEquals(BoatEntity.class, boatMapping.getMappedEntityClass());
    }

    @Test
    @Transactional
    public void testMapEntitiesList() throws Exception {
        List<BoatBean> boatBeans = boatMapping.mapEntities(Lists.newArrayList(BoatEntity.createDummyWithoutId1(), BoatEntity.createDummyWithoutId2()));
        assertEquals(2, boatBeans.size());
    }

    @Test
    @Transactional
    public void testMapEntitiesSet() throws Exception {
        Set<BoatBean> boatBeans = boatMapping.mapEntities(Sets.newHashSet(BoatEntity.createDummyWithId1(), BoatEntity.createDummyWithId2()));
        assertEquals(2, boatBeans.size());
    }

    @Test
    @Transactional
    public void testMapDtosList() throws Exception {
        List<BoatEntity> boatEntities = boatMapping.mapDtos(Lists.newArrayList(BoatBean.createDummyWithoutId1(), BoatBean.createDummyWithoutId2()));
        assertEquals(2, boatEntities.size());
    }

    @Test
    @Transactional
    public void testMapDtosSet() throws Exception {
        Set<BoatEntity> boatEntities = boatMapping.mapDtos(Sets.newHashSet(BoatBean.createDummyWithIdNotExisting1(), BoatBean.createDummyWithIdNotExisting2()));
        assertEquals(2, boatEntities.size());
    }
}
