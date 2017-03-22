package org.boatpos.common.domain.core;

import org.boatpos.common.domain.core.boat.BoatEntity;
import org.boatpos.common.test.EntityManagerProvider;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class JPAHelperTest implements EntityManagerProvider {

    @PersistenceContext(unitName = "my-persistence")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Inject
    private JPAHelper jpaHelper;

    @Test
    @Transactional
    public void testGetEntityManager() throws Exception {
        assertNotNull(jpaHelper.getEntityManager());
    }

    @Test
    @Transactional
    public void testGetSingleResult() throws Exception {
        assertFalse(jpaHelper.getSingleResult(new ArrayList<>()).isPresent());
        assertEquals("a", jpaHelper.getSingleResult(Collections.singletonList("a")).get());
        try {
            jpaHelper.getSingleResult(Arrays.asList("a", "b", "c"));
            fail("non unique result - " + NonUniqueResultException.class.getSimpleName() + " should be thrown");
        } catch (Exception e) {
            // ok
        }
    }

    @Test
    @Transactional
    public void testCreateNamedQuery() throws Exception {
        assertNotNull(jpaHelper.createNamedQuery("boat.getByName", BoatEntity.class));
    }
}