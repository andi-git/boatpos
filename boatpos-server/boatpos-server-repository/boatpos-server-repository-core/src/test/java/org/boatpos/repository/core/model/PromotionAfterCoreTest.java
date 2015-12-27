package org.boatpos.repository.core.model;

import org.boatpos.repository.core.TestUtil;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PromotionAfterCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private TestUtil.PromotionAfterUtil promotionAfterUtil;

    @Test
    @Transactional
    public void testEntity() {
        assertEquals("Alles Gratis", new PromotionAfterCore(promotionAfterUtil.createDummyPromotionAfter().asEntity()).getName().get());
    }

    @Test
    @Transactional
    public void testDto() {
        assertEquals("Alles Gratis", new PromotionAfterCore(promotionAfterUtil.createDummyPromotionAfter().asDto()).getName().get());
    }

    @Test
    @Transactional
    public void testPersist() {
        promotionAfterUtil.assertDatabasePromotionCount(5);
        promotionAfterUtil.createDummyPromotionAfter().persist();
        promotionAfterUtil.assertDatabasePromotionCount(6);
    }
}
