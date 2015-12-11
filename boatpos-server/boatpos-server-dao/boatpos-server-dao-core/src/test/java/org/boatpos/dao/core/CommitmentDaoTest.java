package org.boatpos.dao.core;

import org.boatpos.dao.api.CommitmentDao;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class CommitmentDaoTest extends EntityManagerProviderForBoatpos {

    @Inject
    private CommitmentDao commitmentDao;

    @Test
    @Transactional
    public void testGetByName() {
        assertEquals(1, commitmentDao.getByName("Ausweis").get().getPriority().intValue());
    }

    @Test
    @Transactional
    public void testGetAllEnabled() {
        assertEquals(5, commitmentDao.getAllEnabled().size());
    }


    @Test
    @Transactional
    public void testGetAll() {
        assertEquals(6, commitmentDao.getAll().size());
    }

    @Test
    @Transactional
    public void testGetAllDisabled() {
        assertEquals(1, commitmentDao.getAllDisabled().size());
    }

}