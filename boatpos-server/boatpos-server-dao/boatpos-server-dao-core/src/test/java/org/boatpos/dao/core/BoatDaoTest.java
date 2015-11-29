package org.boatpos.dao.core;

import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class BoatDaoTest extends EntityManagerProviderForBoatpos {

    @Inject
    private BoatDao boatDao;

    @Test
    @Transactional
    public void testGetById() {
        assertEquals("E-Boot", boatDao.getById(1L).get().getName());
    }
}