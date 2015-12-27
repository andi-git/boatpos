package org.boatpos.repository.core.repository;

import org.boatpos.repository.api.repository.PromotionBeforeRepository;
import org.boatpos.repository.api.values.Enabled;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.core.TestUtil;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PromotionBeforeRepositoryCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private TestUtil.PromotionBeforeUtil promotionBeforeUtil;

    @Inject
    private PromotionBeforeRepository promotionBeforeRepository;

    @Test
    @Transactional
    public void testLoadBy() throws Exception {
        assertEquals(180, promotionBeforeRepository.loadBy(new Name("Fahr 3 zahl 2")).get().getTimeCredit().get().intValue());
    }

    @Test
    @Transactional
    public void testLoadAll() throws Exception {
        assertEquals(3, promotionBeforeRepository.loadAll().size());
    }

    @Test
    @Transactional
    public void testLoadAllEnabled() throws Exception {
        assertEquals(2, promotionBeforeRepository.loadAll(Enabled.TRUE).size());
        assertEquals(1, promotionBeforeRepository.loadAll(Enabled.FALSE).size());
    }
}