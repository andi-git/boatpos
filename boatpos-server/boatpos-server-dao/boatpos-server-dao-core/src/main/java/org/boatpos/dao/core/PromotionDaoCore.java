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
public class PromotionDaoCore extends GenericMasterDataDaoCore<Promotion> implements PromotionDao {

    @Override
    public Class<Promotion> getType() {
        return Promotion.class;
    }

    @Override
    public Optional<Promotion> getByName(String name) {
        return getSingleResult(createTypedNamedQuery("promotion.getByName")
                .setParameter("name", name));
    }

    @Override
    protected String nameForGetAll() {
        return "promotion.getAll";
    }

    @Override
    protected String nameForGetAllEnabled() {
        return "promotion.getAllEnabled";
    }

    @Override
    protected String nameForGetAllDisabled() {
        return "promotion.getAllDisabled";
    }

    @Override
    public List<PromotionBefore> getAllBeforeRental() {
        return filter(getAll(), PromotionBefore.class);
    }

    @Override
    public List<PromotionBefore> getAllBeforeRentalEnabled() {
        return filter(getAllEnabled(), PromotionBefore.class);
    }

    @Override
    public List<PromotionBefore> getAllBeforeRentalDisabled() {
        return filter(getAllDisabled(), PromotionBefore.class);
    }

    @Override
    public List<PromotionAfter> getAllAfterRental() {
        return filter(getAll(), PromotionAfter.class);
    }

    @Override
    public List<PromotionAfter> getAllAfterRentalEnabled() {
        return filter(getAllEnabled(), PromotionAfter.class);
    }

    @Override
    public List<PromotionAfter> getAllAfterRentalDisabled() {
        return filter(getAllDisabled(), PromotionAfter.class);
    }

    private <T extends Promotion> List<T> filter(List<Promotion> promotions, Class<T> promotionType) {
        return promotions.stream()
                .filter(p -> p.getClass() == promotionType)
                .map(promotionType::cast)
                .collect(Collectors.toList());
    }
}
