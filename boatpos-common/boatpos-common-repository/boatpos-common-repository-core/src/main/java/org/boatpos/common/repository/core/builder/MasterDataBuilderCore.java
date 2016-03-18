package org.boatpos.common.repository.core.builder;

import org.boatpos.common.model.AbstractMasterDataEntity;
import org.boatpos.common.repository.api.builder.MasterDataBuilder;
import org.boatpos.common.repository.api.model.MasterData;
import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.repository.core.model.MasterDataCore;
import org.boatpos.common.service.api.bean.AbstractMasterDataBean;

public abstract class MasterDataBuilderCore<BUILDER extends MasterDataBuilder, MODEL extends MasterData, MODELCORE extends MasterDataCore, ENTITY extends AbstractMasterDataEntity, DTO extends AbstractMasterDataBean> extends DomainModelBuilderCore<BUILDER, MODEL, MODELCORE, ENTITY, DTO> implements MasterDataBuilder<BUILDER, MODEL, ENTITY, DTO> {

    protected Enabled enabled;
    protected Priority priority;
    protected KeyBinding keyBinding;
    protected PictureUrlThumb pictureUrlThumb;
    protected PictureUrl pictureUrl;

    @Override
    public BUILDER add(Enabled enabled) {
        this.enabled = enabled;
        //noinspection unchecked
        return (BUILDER) this;
    }

    @Override
    public BUILDER add(Priority priority) {
        this.priority = priority;
        //noinspection unchecked
        return (BUILDER) this;

    }

    @Override
    public BUILDER add(KeyBinding keyBinding) {
        this.keyBinding = keyBinding;
        //noinspection unchecked
        return (BUILDER) this;
    }

    @Override
    public BUILDER add(PictureUrlThumb pictureUrlThumb) {
        this.pictureUrlThumb = pictureUrlThumb;
        //noinspection unchecked
        return (BUILDER) this;
    }

    @Override
    public BUILDER add(PictureUrl pictureUrl) {
        this.pictureUrl = pictureUrl;
        //noinspection unchecked
        return (BUILDER) this;
    }
}