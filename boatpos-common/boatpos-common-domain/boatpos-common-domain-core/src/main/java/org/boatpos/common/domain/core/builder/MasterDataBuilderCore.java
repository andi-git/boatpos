package org.boatpos.common.domain.core.builder;

import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.domain.core.model.MasterDataCore;
import org.boatpos.common.model.AbstractMasterDataEntity;
import org.boatpos.common.domain.api.builder.MasterDataBuilder;
import org.boatpos.common.domain.api.model.MasterData;

public abstract class MasterDataBuilderCore<BUILDER extends MasterDataBuilder, MODEL extends MasterData, MODELCORE extends MasterDataCore, ENTITY extends AbstractMasterDataEntity>
        extends DomainModelBuilderCore<BUILDER, MODEL, MODELCORE, ENTITY>
        implements MasterDataBuilder<BUILDER, MODEL, ENTITY> {

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
