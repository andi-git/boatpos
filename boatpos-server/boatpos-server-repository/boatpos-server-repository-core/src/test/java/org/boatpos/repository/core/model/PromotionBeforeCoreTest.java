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
public class PromotionBeforeCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private TestUtil.PromotionBeforeUtil promotionBeforeUtil;

    @Test
    @Transactional
    public void testEntity() {
        assertEquals("Tageskarte", new PromotionBeforeCore(promotionBeforeUtil.createDummyPromotionBefore().asEntity()).getName().get());
    }

    @Test
    @Transactional
    public void testDto() {
        assertEquals("Tageskarte", new PromotionBeforeCore(promotionBeforeUtil.createDummyPromotionBefore().asDto()).getName().get());
    }

    @Test
    @Transactional
    public void testPersist() {
        promotionBeforeUtil.assertDatabasePromotionCount(5);
        promotionBeforeUtil.createDummyPromotionBefore().persist();
        promotionBeforeUtil.assertDatabasePromotionCount(6);
    }
}
