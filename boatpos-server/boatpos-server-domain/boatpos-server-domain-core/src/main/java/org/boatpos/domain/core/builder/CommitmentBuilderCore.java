package org.boatpos.domain.core.builder;

import org.boatpos.common.domain.core.builder.MasterDataBuilderCoreWithDto;
import org.boatpos.model.CommitmentEntity;
import org.boatpos.domain.api.builder.CommitmentBuilder;
import org.boatpos.domain.api.model.Commitment;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.api.values.Paper;
import org.boatpos.domain.core.model.CommitmentCore;
import org.boatpos.service.api.bean.CommitmentBean;

import javax.enterprise.context.Dependent;
import java.util.HashSet;
import java.util.Set;

@Dependent
public class CommitmentBuilderCore extends MasterDataBuilderCoreWithDto<CommitmentBuilder, Commitment, CommitmentCore, CommitmentEntity, CommitmentBean> implements CommitmentBuilder {

    private Name name;
    private Paper paper;
    private Set<Rental> rentals = new HashSet<>();

    @Override
    public CommitmentBuilder add(Name name) {
        this.name = name;
        return this;
    }

    @Override
    public CommitmentBuilder add(Paper paper) {
        this.paper = paper;
        return this;
    }

    @Override
    public CommitmentBuilder add(Set<Rental> rentals) {
        this.rentals.addAll(rentals);
        return this;
    }

    @Override
    public CommitmentBuilder add(Rental rental) {
        this.rentals.add(rental);
        return this;
    }

    @Override
    public Commitment build() {
        return new CommitmentCore(id, version, enabled, priority, name, paper, rentals, keyBinding, pictureUrl, pictureUrlThumb);
    }
}
