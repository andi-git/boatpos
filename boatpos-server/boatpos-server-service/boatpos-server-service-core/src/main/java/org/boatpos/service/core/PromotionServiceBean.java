package org.boatpos.service.core;

import org.boatpos.dao.api.PromotionDao;
import org.boatpos.model.Promotion;
import org.boatpos.service.api.PromotionService;
import org.boatpos.service.api.bean.PromotionAfterBean;
import org.boatpos.service.api.bean.PromotionBean;
import org.boatpos.service.api.bean.PromotionBeforeBean;
import org.boatpos.service.core.mapping.PromotionAfterMapping;
import org.boatpos.service.core.mapping.PromotionBeforeMapping;
import org.boatpos.service.core.mapping.PromotionMapping;
import org.boatpos.service.core.util.GenericCrudHelper;
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
    private GenericCrudHelper crudHelper;

    @Override
    public List<PromotionBean> getAll() {
        return promotionMapping.mapEntities(promotionDao.getAll());
    }

    @Override
    public List<PromotionBeforeBean> getAllBeforeRental() {
        return promotionBeforeMapping.mapEntities(promotionDao.getAllBeforeRental());
    }

    @Override
    public List<PromotionAfterBean> getAllAfterRental() {
        return promotionAfterMapping.mapEntities(promotionDao.getAllAfterRental());
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
    public void delete(Long id) {
        promotionDao.delete(id);
    }
}
