package org.boatpos.dao.core;

import org.boatpos.dao.api.CommitmentDao;
import org.boatpos.dao.api.PromotionDao;
import org.boatpos.model.Promotion;
import org.boatpos.model.PromotionAfter;
import org.boatpos.model.PromotionBefore;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

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
    public void testGetAll() {
        assertEquals(5, commitmentDao.getAll().size());
    }
}