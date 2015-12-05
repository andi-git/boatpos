package org.boatpos.dao.core;

import org.boatpos.dao.api.PromotionDao;
import org.boatpos.model.Promotion;
import org.boatpos.model.PromotionAfter;
import org.boatpos.model.PromotionBefore;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<Promotion> getAll() {
        return createNamedQuery("promotion.getAll").getResultList();
    }

    @Override
    public List<PromotionBefore> getAllBeforeRental() {
        return getAll().stream()
                .filter(p -> p instanceof PromotionBefore)
                .map(PromotionBefore.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public List<PromotionAfter> getAllAfterRental() {
        return getAll().stream()
                .filter(p -> p instanceof PromotionAfter)
                .map(PromotionAfter.class::cast)
                .collect(Collectors.toList());
    }

}
