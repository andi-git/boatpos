package org.boatpos.repository.core.builder;

import org.boatpos.model.AbstractMasterDataEntity;
import org.boatpos.repository.api.builder.MasterDataBuilder;
import org.boatpos.repository.api.model.MasterData;
import org.boatpos.repository.api.values.Enabled;
import org.boatpos.repository.api.values.KeyBinding;
import org.boatpos.repository.api.values.Priority;
import org.boatpos.repository.core.model.MasterDataCore;
import org.boatpos.service.api.bean.AbstractMasterDataBean;

public abstract class MasterDataBuilderCore<BUILDER extends MasterDataBuilder, MODEL extends MasterData, MODELCORE extends MasterDataCore, ENTITY extends AbstractMasterDataEntity, DTO extends AbstractMasterDataBean> extends DomainModelBuilderCore<BUILDER, MODEL, MODELCORE, ENTITY, DTO> implements MasterDataBuilder<BUILDER, MODEL, ENTITY, DTO> {

    protected Enabled enabled;
    protected Priority priority;
    protected KeyBinding keyBinding;

    public BUILDER add(Enabled enabled) {
        this.enabled = enabled;
        //noinspection unchecked
        return (BUILDER) this;
    }

    public BUILDER add(Priority priority) {
        this.priority = priority;
        //noinspection unchecked
        return (BUILDER) this;
    }

    public BUILDER add(KeyBinding keyBinding) {
        this.keyBinding = keyBinding;
        //noinspection unchecked
        return (BUILDER) this;
    }

}
