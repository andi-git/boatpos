package org.boatpos.domain.api.model;

import org.boatpos.common.domain.api.model.MasterDataWithDto;
import org.boatpos.model.PromotionEntity;
import org.boatpos.domain.api.values.FormulaPrice;
import org.boatpos.domain.api.values.Name;
import org.boatpos.service.api.bean.PromotionBean;

/**
 * The domain model for a promotion.
 */
public interface Promotion<MODEL extends Promotion, ENTITY extends PromotionEntity, DTO extends PromotionBean> extends MasterDataWithDto<MODEL, ENTITY, DTO>, ContainsRentals<MODEL> {

    Name getName();

    MODEL setName(Name name);

    FormulaPrice getFormulaPrice();

    MODEL setFormulaPrice(FormulaPrice formulaPrice);

    @Override
    ENTITY asEntity();
}
