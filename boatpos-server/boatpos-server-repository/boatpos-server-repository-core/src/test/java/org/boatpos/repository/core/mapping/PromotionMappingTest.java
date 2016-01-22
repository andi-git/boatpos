package org.boatpos.repository.core.mapping;

import org.boatpos.model.PromotionAfterEntity;
import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.service.api.bean.PromotionAfterBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PromotionMappingTest {

    @Inject
    private PromotionMapping promotionMapping;

    private PromotionBeforeEntity promotionBefore;

    private PromotionAfterEntity promotionAfter;

    private PromotionBeforeBean promotionBeforeBean;

    private PromotionAfterBean promotionAfterBean;

    @Before
    public void before() {
        promotionBefore = new PromotionBeforeEntity(1L, 1, "PromoB", 60, "calcPrice", new HashSet<>(), 1, true, 'a');
        promotionBeforeBean = new PromotionBeforeBean(1L, 1, "PromoB", 60, "calcPrice", 1, true, 'a');
        promotionAfter = new PromotionAfterEntity(2L, 1, "PromoA", "calcPrice", new HashSet<>(), 1, true, 'a');
        promotionAfterBean = new PromotionAfterBean(2L, 1, "PromoA", "calcPrice", 1, true, 'a');
    }

    @Test
    public void testMapEntityToType() throws Exception {
        assertEquals("PromoA", promotionMapping.mapEntity(promotionAfter).getName());
        assertEquals("PromoB", promotionMapping.mapEntity(promotionBefore).getName());
    }

    @Test
    public void testMapEntityToObject() throws Exception {
        PromotionAfterBean beanAfter = new PromotionAfterBean();
        promotionMapping.mapEntity(promotionAfter, beanAfter);
        assertEquals("PromoA", beanAfter.getName());

        PromotionBeforeBean beanBefore = new PromotionBeforeBean();
        promotionMapping.mapEntity(promotionBefore, beanBefore);
        assertEquals("PromoB", beanBefore.getName());
    }

    @Test
    public void testMapDtoToType() throws Exception {
        assertEquals("PromoA", promotionMapping.mapDto(promotionAfterBean).getName());
        assertEquals("PromoB", promotionMapping.mapDto(promotionBeforeBean).getName());
    }

    @Test
    public void testMapDtoToObject() throws Exception {
        PromotionAfterEntity after = new PromotionAfterEntity();
        promotionMapping.mapDto(promotionAfterBean, after);
        assertEquals("PromoA", after.getName());

        PromotionBeforeEntity before = new PromotionBeforeEntity();
        promotionMapping.mapDto(promotionBeforeBean, before);
        assertEquals("PromoB", before.getName());
    }
}