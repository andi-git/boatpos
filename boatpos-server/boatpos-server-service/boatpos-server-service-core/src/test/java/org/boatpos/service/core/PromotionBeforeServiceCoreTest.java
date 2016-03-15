package org.boatpos.service.core;

import org.boatpos.common.service.api.EnabledState;
import org.boatpos.service.api.MasterDataService;
import org.boatpos.service.api.PromotionBeforeService;
import org.boatpos.service.api.bean.PromotionBeforeBean;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PromotionBeforeServiceCoreTest extends AbstractMasterDataServiceTest<PromotionBeforeBean> {

    @Inject
    private PromotionBeforeService promotionBeforeService;

    @Test
    @Transactional
    public void testGetAllBeforeRental() throws Exception {
        assertEquals(3, promotionBeforeService.getAll(EnabledState.All).size());
        assertEquals(2, promotionBeforeService.getAll(EnabledState.Enabled).size());
        assertEquals(1, promotionBeforeService.getAll(EnabledState.Disabled).size());
    }

    @Test
    @Transactional
    public void testGetById() throws Exception {
        Long id = promotionBeforeService.getByName("Fahr 3 zahl 2").get().getId();
        assertEquals("Fahr 3 zahl 2", promotionBeforeService.getById(id).get().getName());
    }

    @Test
    @Transactional
    public void testGetByName() throws Exception {
        assertEquals("Fahr 3 zahl 2", promotionBeforeService.getByName("Fahr 3 zahl 2").get().getName());
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        promotionBeforeService.save(new PromotionBeforeBean(null, 1, "PROMO", 300, "price / 3", 3, true, 'a', "", ""));
        assertEquals(4, promotionBeforeService.getAll().size());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        PromotionBeforeBean promotion = promotionBeforeService.getByName("Fahr 3 zahl 2").get();
        promotion.setName("F3Z2");
        promotionBeforeService.update(promotion);
        assertEquals(2, promotionBeforeService.getByName("F3Z2").get().getVersion().intValue());
        assertEquals(3, promotionBeforeService.getAll(EnabledState.All).size());
    }

    @Override
    protected MasterDataService<PromotionBeforeBean> service() {
        return promotionBeforeService;
    }

    @Override
    protected int countAll() {
        return 3;
    }

    @Override
    protected int countAllEnabled() {
        return 2;
    }

    @Override
    protected int countAllDisabled() {
        return 1;
    }

    @Override
    protected Long idToEnable() {
        return promotionBeforeService.getByName("Tageskarte").get().getId();
    }

    @Override
    protected Long idToDisable() {
        return promotionBeforeService.getByName("Fahr 3 zahl 2").get().getId();
    }
}