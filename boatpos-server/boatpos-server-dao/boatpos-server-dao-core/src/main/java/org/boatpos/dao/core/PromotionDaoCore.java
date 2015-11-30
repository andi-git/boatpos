package org.boatpos.dao.core;

import org.boatpos.dao.api.PromotionDao;
import org.boatpos.model.Promotion;

import javax.enterprise.context.Dependent;

@Dependent
public class PromotionDaoCore extends GenericDaoCore<Promotion> implements PromotionDao {

    @Override
    public Class<Promotion> getType() {
        return Promotion.class;
    }
}
