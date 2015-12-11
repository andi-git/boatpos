package org.boatpos.service.core;

import org.boatpos.dao.api.PromotionDao;
import org.boatpos.model.Promotion;
import org.boatpos.model.PromotionAfter;
import org.boatpos.model.PromotionBefore;
import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.PromotionService;
import org.boatpos.service.api.bean.PromotionAfterBean;
import org.boatpos.service.api.bean.PromotionBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;
import org.boatpos.service.core.mapping.PromotionAfterMapping;
import org.boatpos.service.core.mapping.PromotionBeforeMapping;
import org.boatpos.service.core.mapping.PromotionMapping;
import org.boatpos.service.core.util.CrudHelper;
import org.boatpos.service.core.util.MasterDataHelper;
import org.boatpos.util.log.SLF4J;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class PromotionServiceBean implements PromotionService {

    @Inject
    @SLF4J
    private Logger log;

    @Inject
    private PromotionDao promotionDao;

    @Inject
    private PromotionMapping promotionMapping;

    @Inject
    private PromotionBeforeMapping promotionBeforeMapping;

    @Inject
    private PromotionAfterMapping promotionAfterMapping;

    @Inject
    private CrudHelper crudHelper;

    @Inject
    private MasterDataHelper masterDataHelper;

    @Override
    public List<PromotionBean> getAll() {
        return getAll(EnabledState.All);
    }

    @Override
    public List<PromotionBean> getAll(EnabledState enabledState) {
        return masterDataHelper.getAll(promotionDao, promotionMapping, enabledState);
    }

    @Override
    public List<PromotionBeforeBean> getAllBeforeRental(EnabledState enabledState) {
        List<PromotionBefore> entities;
        if (EnabledState.Enabled == enabledState) {
            entities = promotionDao.getAllBeforeRentalEnabled();
        } else if (EnabledState.Disabled == enabledState) {
            entities = promotionDao.getAllBeforeRentalDisabled();
        } else {
            entities = promotionDao.getAllBeforeRental();
        }
        return promotionBeforeMapping.mapEntities(entities);
    }

    @Override
    public List<PromotionAfterBean> getAllAfterRental(EnabledState enabledState) {
        List<PromotionAfter> entities;
        if (EnabledState.Enabled == enabledState) {
            entities = promotionDao.getAllAfterRentalEnabled();
        } else if (EnabledState.Disabled == enabledState) {
            entities = promotionDao.getAllAfterRentalDisabled();
        } else {
            entities = promotionDao.getAllAfterRental();
        }
        return promotionAfterMapping.mapEntities(entities);
    }

    @Override
    public PromotionBean getById(Long id) {
        return crudHelper.getOrNull(promotionDao.getById(id), promotionMapping, () -> log.error("no {} available with id {}", Promotion.class.getName(), id));
    }

    @Override
    public PromotionBean getByName(String name) {
        return crudHelper.getOrNull(promotionDao.getByName(name), promotionMapping, () -> log.error("no {} available with name {}", Promotion.class.getName(), name));
    }

    @Override
    public PromotionBean save(PromotionBean promotionBean) {
        return promotionMapping.mapEntity(promotionDao.save(promotionMapping.mapDto(promotionBean)));
    }

    @Override
    public PromotionBean update(PromotionBean promotionBean) {
        Optional<PromotionBean> updatedDto = crudHelper.update(promotionBean, promotionDao, promotionMapping, () -> log.error("unable to update {} {}", Promotion.class.getName(), promotionBean));
        return crudHelper.getOrNull(updatedDto, () -> {
        });
    }


    @Override
    public void enable(Long id) {
        masterDataHelper.enable(id, promotionDao);
    }

    @Override
    public void disable(Long id) {
        masterDataHelper.disable(id, promotionDao);
    }

}
