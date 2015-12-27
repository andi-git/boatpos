package org.boatpos.repository.api.model;

import org.boatpos.model.PromotionEntity;
import org.boatpos.model.RentalEntity;
import org.boatpos.repository.api.values.FormulaPrice;
import org.boatpos.repository.api.values.Name;
import org.boatpos.service.api.bean.PromotionBean;

import java.util.Set;

/**
 * The domain model for a promotion.
 */
public interface Promotion<MODEL extends Promotion, ENTITY extends PromotionEntity, DTO extends PromotionBean> extends MasterData<MODEL, ENTITY, DTO>, ContainsRentals<MODEL> {

    Name getName();

    MODEL setName(Name name);

    FormulaPrice getFormulaPrice();

    MODEL setFormulaPrice(FormulaPrice formulaPrice);

    @Override
    ENTITY asEntity();
}
