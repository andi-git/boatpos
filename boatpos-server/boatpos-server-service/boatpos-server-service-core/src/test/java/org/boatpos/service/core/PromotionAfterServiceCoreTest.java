package org.boatpos.service.core;

import org.boatpos.common.service.api.EnabledState;
import org.boatpos.common.service.api.MasterDataService;
import org.boatpos.service.api.PromotionAfterService;
import org.boatpos.service.api.bean.PromotionAfterBean;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PromotionAfterServiceCoreTest extends AbstractMasterDataServiceTest<PromotionAfterBean> {

    @Inject
    private PromotionAfterService promotionAfterService;

    @Test
    @Transactional
    public void testGetAllAfterRental() throws Exception {
        assertEquals(2, promotionAfterService.getAll(EnabledState.All).size());
        assertEquals(1, promotionAfterService.getAll(EnabledState.Enabled).size());
        assertEquals(1, promotionAfterService.getAll(EnabledState.Disabled).size());
    }

    @Test
    @Transactional
    public void testGetById() throws Exception {
        Long id = promotionAfterService.getByName("HolliKnolli").get().getId();
        assertEquals("HolliKnolli", promotionAfterService.getById(id).get().getName());
    }

    @Test
    @Transactional
    public void testGetByName() throws Exception {
        assertEquals("HolliKnolli", promotionAfterService.getByName("HolliKnolli").get().getName());
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        assertEquals(2, promotionAfterService.getAll(EnabledState.All).size());
        promotionAfterService.save(new PromotionAfterBean(null, 1, "PROMO", "price / 3", 3, true, 'a', "", ""));
        assertEquals(3, promotionAfterService.getAll(EnabledState.All).size());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        PromotionAfterBean promotion = promotionAfterService.getByName("HolliKnolli").get();
        promotion.setName("HKn");
        promotionAfterService.update(promotion);
        assertEquals(2, promotionAfterService.getByName("HKn").get().getVersion().intValue());
        assertEquals(2, promotionAfterService.getAll(EnabledState.All).size());
    }

    @Override
    protected MasterDataService<PromotionAfterBean> service() {
        return promotionAfterService;
    }

    @Override
    protected int countAll() {
        return 2;
    }

    @Override
    protected int countAllEnabled() {
        return 1;
    }

    @Override
    protected int countAllDisabled() {
        return 1;
    }

    @Override
    protected Long idToEnable() {
        return promotionAfterService.getByName("Sommerferien").get().getId();
    }

    @Override
    protected Long idToDisable() {
        return promotionAfterService.getByName("HolliKnolli").get().getId();
    }
}