package org.boatpos.dao.core;

import org.boatpos.dao.api.PromotionDao;
import org.boatpos.model.Promotion;

import javax.enterprise.context.Dependent;
import java.util.Optional;

@Dependent
public class PromotionDaoCore extends GenericDaoCore<Promotion> implements PromotionDao {

    @Override
    public Class<Promotion> getType() {
        return Promotion.class;
    }

    @Override
    public Optional<Promotion> getByName(String name) {
        return getSingleResult(createNamedQuery("promotion.getByName")
                .setParameter("name", name));
    }
}
