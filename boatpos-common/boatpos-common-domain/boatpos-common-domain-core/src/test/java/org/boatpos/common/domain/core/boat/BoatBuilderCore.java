package org.boatpos.common.domain.core.boat;

import org.boatpos.common.domain.core.builder.MasterDataBuilderCoreWithDto;

import javax.enterprise.context.Dependent;

@Dependent
public class BoatBuilderCore extends MasterDataBuilderCoreWithDto<BoatBuilder, Boat, BoatCore, BoatEntity, BoatBean> implements BoatBuilder {

    private Name name;
    private Price price;

    @Override
    public BoatBuilder add(Name name) {
        this.name = name;
        return this;
    }

    @Override
    public BoatBuilder add(Price price) {
        this.price = price;
        return this;
    }

    @Override
    public Boat build() {
        return new BoatCore(id, version, enabled, priority, name, price, keyBinding, pictureUrl, pictureUrlThumb);
    }

    @Override
    public Class<BoatCore> getDomainModelCoreClass() {
        return super.getDomainModelCoreClass();
    }

    @Override
    public Class<BoatEntity> getEntityClass() {
        return super.getEntityClass();
    }
}
