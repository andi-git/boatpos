package org.boatpos.repository.core.model;

import org.boatpos.common.repository.api.values.*;
import org.boatpos.model.CommitmentEntity;
import org.boatpos.repository.api.model.Commitment;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.values.*;
import org.boatpos.repository.core.mapping.CommitmentMapping;
import org.boatpos.service.api.bean.CommitmentBean;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class CommitmentCore extends MasterDataCore<Commitment, CommitmentEntity, CommitmentBean> implements Commitment {

    public CommitmentCore(DomainId id,
                          Version version,
                          Enabled enabled,
                          Priority priority,
                          Name name,
                          Paper paper,
                          Set<Rental> rentals,
                          KeyBinding keyBinding,
                          PictureUrl pictureUrl,
                          PictureUrlThumb pictureUrlThumb) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(enabled, "'enabled' must not be null");
        checkNotNull(name, "'name' must not be null");
        checkNotNull(paper, "'paper' must not be null");
        setName(name);
        setPaper(paper);
        setRentals(rentals);
    }

    public CommitmentCore(CommitmentEntity commitmentEntity) {
        super(commitmentEntity);
    }

    public CommitmentCore(CommitmentBean commitmentBean) {
        this(CommitmentMapping.fromCDI().mapDto(commitmentBean));
    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    @Override
    public Commitment setName(Name name) {
        getEntity().setName(SimpleValueObject.nullSafe(name));
        return this;
    }

    @Override
    public Paper needPaper() {
        return new Paper(getEntity().getPaper());
    }

    @Override
    public Commitment setPaper(Paper paper) {
        getEntity().setPaper(SimpleValueObject.nullSafe(paper));
        return this;
    }

    @Override
    public Set<Rental> getRentals() {
        return RentalConverter.getViaCDI().convert(getEntity().getRentals());
    }

    @Override
    public Commitment setRentals(Set<Rental> rentals) {
        RentalConverter.getViaCDI().setRentals(getEntity(), rentals);
        return this;
    }

    @Override
    public Commitment addRental(Rental rental) {
        RentalConverter.getViaCDI().addRental(getEntity(), rental);
        return this;
    }

    @Override
    public CommitmentBean asDto() {
        return CommitmentMapping.fromCDI().mapEntity(getEntity());
    }
}