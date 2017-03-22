package org.boatpos.domain.core.model;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.common.domain.api.values.Version;
import org.boatpos.common.domain.core.model.MasterDataCore;
import org.boatpos.model.PromotionEntity;
import org.boatpos.domain.api.model.Promotion;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.api.values.FormulaPrice;
import org.boatpos.domain.api.values.Name;
import org.boatpos.service.api.bean.PromotionBean;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class PromotionCore<MODEL extends Promotion, ENTITY extends PromotionEntity, DTO extends PromotionBean>
        extends MasterDataCore<MODEL, ENTITY>
        implements Promotion<MODEL, ENTITY, DTO> {

    public PromotionCore(DomainId id,
                         Version version,
                         Enabled enabled,
                         Priority priority,
                         Name name,
                         FormulaPrice formulaPrice,
                         Set<Rental> rentals,
                         KeyBinding keyBinding,
                         PictureUrl pictureUrl,
                         PictureUrlThumb pictureUrlThumb) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(enabled, "'enabled' must not be null");
        checkNotNull(name, "'name' must not be null");
        checkNotNull(formulaPrice, "'formulaPrice' must not be null");
        setName(name);
        setFormulaPrice(formulaPrice);
        setRentals(rentals);
    }

    public PromotionCore(ENTITY promotionEntity) {
        super(promotionEntity);
    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    @Override
    public MODEL setName(Name name) {
        getEntity().setName(SimpleValueObject.nullSafe(name));
        //noinspection unchecked
        return (MODEL) this;
    }

    @Override
    public FormulaPrice getFormulaPrice() {
        return new FormulaPrice(getEntity().getFormulaPrice());
    }

    @Override
    public MODEL setFormulaPrice(FormulaPrice formulaPrice) {
        getEntity().setFormulaPrice(SimpleValueObject.nullSafe(formulaPrice));
        //noinspection unchecked
        return (MODEL) this;
    }

    @Override
    public Set<Rental> getRentals() {
        return RentalConverter.getViaCDI().convert(getEntity().getRentals());
    }

    @Override
    public MODEL setRentals(Set<Rental> rentals) {
        RentalConverter.getViaCDI().setRentals(getEntity(), rentals);
        //noinspection unchecked
        return (MODEL) this;
    }

    @Override
    public MODEL addRental(Rental rental) {
        RentalConverter.getViaCDI().addRental(getEntity(), rental);
        //noinspection unchecked
        return (MODEL) this;
    }
}