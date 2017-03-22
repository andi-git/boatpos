package org.boatpos.domain.core.builder;

import org.boatpos.common.domain.core.builder.MasterDataBuilderCoreWithDto;
import org.boatpos.model.PromotionEntity;
import org.boatpos.domain.api.builder.PromotionBuilder;
import org.boatpos.domain.api.model.Promotion;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.api.values.FormulaPrice;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.core.model.PromotionCore;
import org.boatpos.service.api.bean.PromotionBean;

import java.util.HashSet;
import java.util.Set;

public abstract class PromotionBuilderCore<BUILDER extends PromotionBuilder, MODEL extends Promotion, MODELCORE extends PromotionCore, ENTITY extends PromotionEntity, DTO extends PromotionBean>
        extends MasterDataBuilderCoreWithDto<BUILDER, MODEL, MODELCORE, ENTITY, DTO>
        implements PromotionBuilder<BUILDER, MODEL, ENTITY, DTO> {

    protected Name name;
    protected FormulaPrice formulaPrice;
    protected Set<Rental> rentals = new HashSet<>();

    @Override
    public BUILDER add(Name name) {
        this.name = name;
        //noinspection unchecked
        return (BUILDER) this;
    }

    @Override
    public BUILDER add(FormulaPrice formulaPrice) {
        this.formulaPrice = formulaPrice;
        //noinspection unchecked
        return (BUILDER) this;
    }

    @Override
    public BUILDER add(Rental rental) {
        this.rentals.add(rental);
        //noinspection unchecked
        return (BUILDER) this;
    }

    @Override
    public BUILDER add(Set<Rental> rentals) {
        this.rentals.addAll(rentals);
        //noinspection unchecked
        return (BUILDER) this;
    }
}
