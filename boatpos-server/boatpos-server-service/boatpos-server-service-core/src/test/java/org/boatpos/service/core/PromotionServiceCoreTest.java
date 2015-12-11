package org.boatpos.service.core;

import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.MasterDataService;
import org.boatpos.service.api.PromotionService;
import org.boatpos.service.api.bean.PromotionBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PromotionServiceCoreTest extends AbstractMasterDataServiceTest<PromotionBean> {

    @Inject
    private PromotionService promotionService;

    @Test
    @Transactional
    public void testGetAllBeforeRental() throws Exception {
        assertEquals(3, promotionService.getAllBeforeRental(EnabledState.All).size());
        assertEquals(2, promotionService.getAllBeforeRental(EnabledState.Enabled).size());
        assertEquals(1, promotionService.getAllBeforeRental(EnabledState.Disabled).size());
    }

    @Test
    @Transactional
    public void testGetAllAfterRental() throws Exception {
        assertEquals(2, promotionService.getAllAfterRental(EnabledState.All).size());
        assertEquals(1, promotionService.getAllAfterRental(EnabledState.Enabled).size());
        assertEquals(1, promotionService.getAllAfterRental(EnabledState.Disabled).size());
    }

    @Test
    @Transactional
    public void testGetById() throws Exception {
        assertEquals("Fahr 3 zahl 2", promotionService.getById(3L).get().getName());
    }

    @Test
    @Transactional
    public void testGetByName() throws Exception {
        assertEquals(3L, promotionService.getByName("Fahr 3 zahl 2").get().getId().longValue());
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        promotionService.save(new PromotionBeforeBean(null, 1, "PROMO", 300, "price / 3", 3, true));
        assertEquals(6, promotionService.getAll(EnabledState.All).size());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        PromotionBean promotionBean = promotionService.getByName("Fahr 3 zahl 2").get();
        promotionBean.setName("F3Z2");
        promotionService.update(promotionBean);
        assertEquals(2, promotionService.getByName("F3Z2").get().getVersion().intValue());
        assertEquals(5, promotionService.getAll(EnabledState.All).size());
    }

    @Override
    protected MasterDataService<PromotionBean> service() {
        return promotionService;
    }

    @Override
    protected int countAll() {
        return 5;
    }

    @Override
    protected int countAllEnabled() {
        return 3;
    }

    @Override
    protected int countAllDisabled() {
        return 2;
    }

    @Override
    protected Long idToEnable() {
        return promotionService.getByName("Sommerferien").get().getId();
    }

    @Override
    protected Long idToDisable() {
        return promotionService.getByName("Fahr 3 zahl 2").get().getId();
    }
}