package org.boatpos.common.repository.core.repository;

import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.repository.core.boat.BoatBuilderCore;
import org.boatpos.common.repository.core.boat.BoatRepository;
import org.boatpos.common.repository.core.boat.Name;
import org.boatpos.common.test.EntityManagerProvider;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class RepositoryTest implements EntityManagerProvider {

    @PersistenceContext(unitName = "my-persistence")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Inject
    private BoatRepository boatRepository;

    @Test
    @Transactional
    public void testLoadById() throws Exception {
        assertEquals("E-Boot", boatRepository.loadBy(new DomainId(1L)).get().getName().get());
    }

    @Test
    @Transactional
    public void testLoadAll() throws Exception {
        assertEquals(6, boatRepository.loadAll().size());
        assertEquals(5, boatRepository.loadAll(Enabled.TRUE).size());
        assertEquals(1, boatRepository.loadAll(Enabled.FALSE).size());
    }

    @Test
    @Transactional
    public void testBuilder() throws Exception {
        assertEquals(BoatBuilderCore.class, boatRepository.builder().getClass());
    }

    @Test
    @Transactional
    public void testLoadByName() throws Exception {
        assertEquals("E-Boot", boatRepository.loadBy(new Name("E-Boot")).get().getName().get());
    }

    @Test
    @Transactional
    public void testLoad() throws Exception {
        assertEquals("E-Boot", boatRepository.loadEBoot().getName().get());
    }
}
