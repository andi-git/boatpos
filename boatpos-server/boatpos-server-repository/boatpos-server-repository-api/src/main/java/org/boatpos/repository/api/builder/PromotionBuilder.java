package org.boatpos.repository.api.builder;

import org.boatpos.common.repository.api.builder.MasterDataBuilder;
import org.boatpos.model.PromotionEntity;
import org.boatpos.model.RentalEntity;
import org.boatpos.repository.api.model.Promotion;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.values.FormulaPrice;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.api.values.Paper;
import org.boatpos.service.api.bean.PromotionBean;

import java.util.Set;

/**
 * Builder for {@link Promotion}.
 */
public interface PromotionBuilder<BUILDER extends PromotionBuilder, MODEL extends Promotion, ENTITY extends PromotionEntity, DTO extends PromotionBean> extends MasterDataBuilder<BUILDER, MODEL, ENTITY, DTO> {

    BUILDER add(Name name);

    BUILDER add(FormulaPrice formulaPrice);

    BUILDER add(Set<Rental> rentals);

    BUILDER add(Rental rental);
}
