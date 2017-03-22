package org.boatpos.repository.core.repository;

import org.boatpos.common.repository.api.values.Enabled;
import org.boatpos.repository.api.model.PromotionAfter;
import org.boatpos.repository.api.repository.PromotionAfterRepository;
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
public class PromotionAfterRepositoryCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private TestUtil.PromotionAfterUtil promotionAfterUtil;

    @Inject
    private PromotionAfterRepository promotionAftRepository;

    @Test
    @Transactional
    public void testLoadBy() throws Exception {
        PromotionAfter holliKnolli = promotionAftRepository.loadBy(new Name("HolliKnolli")).get();
        assertEquals("HolliKnolli", holliKnolli.getName().get());
        assertEquals("price / 2", holliKnolli.getFormulaPrice().get());
    }

    @Test
    @Transactional
    public void testLoadAll() throws Exception {
        assertEquals(2, promotionAftRepository.loadAll().size());
    }

    @Test
    @Transactional
    public void testLoadAllEnabled() throws Exception {
        assertEquals(1, promotionAftRepository.loadAll(Enabled.TRUE).size());
        assertEquals(1, promotionAftRepository.loadAll(Enabled.FALSE).size());
    }
}