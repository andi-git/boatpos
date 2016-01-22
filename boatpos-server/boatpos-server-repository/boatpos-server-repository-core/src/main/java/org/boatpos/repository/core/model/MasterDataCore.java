package org.boatpos.repository.core.model;

import org.boatpos.model.AbstractMasterDataEntity;
import org.boatpos.repository.api.model.MasterData;
import org.boatpos.repository.api.values.*;
import org.boatpos.service.api.bean.AbstractMasterDataBean;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class MasterDataCore<MODEL extends MasterData, ENTITY extends AbstractMasterDataEntity, DTO extends AbstractMasterDataBean> extends DomainModelCore<MODEL, ENTITY, DTO> implements MasterData<MODEL, ENTITY, DTO> {

    public MasterDataCore(DomainId id, Version version, Enabled enabled, Priority priority, KeyBinding keyBinding) {
        super(id, version);
        checkNotNull(enabled, "'enabled' must not be null");
        checkNotNull(priority, "'priority' must not be null");
        setEnabled(enabled);
        setPriority(priority);
    }

    public MasterDataCore(ENTITY abstractMasterDataEntity) {
        super(abstractMasterDataEntity);
    }

    @Override
    public MODEL enable() {
        setEnabled(Enabled.TRUE);
        persist();
        //noinspection unchecked
        return (MODEL) this;
    }

    @Override
    public MODEL disable() {
        setEnabled(Enabled.FALSE);
        persist();
        //noinspection unchecked
        return (MODEL) this;
    }

    @Override
    public void delete() {
        setEnabled(Enabled.FALSE);
        persist();
    }

    @Override
    public Enabled isEnabled() {
        return new Enabled(getEntity().isEnabled());
    }

    @Override
    public MODEL setEnabled(Enabled enabled) {
        getEntity().setEnabled(SimpleValueObject.nullSafe(enabled));
        //noinspection unchecked
        return (MODEL) this;
    }

    @Override
    public Priority getPriority() {
        return new Priority(getEntity().getPriority());
    }

    @Override
    public MODEL setPriority(Priority priority) {
        getEntity().setPriority(SimpleValueObject.nullSafe(priority));
        //noinspection unchecked
        return (MODEL) this;
    }

    @Override
    public KeyBinding getKeyBinding() {
        return new KeyBinding(getEntity().getKeyBinding());
    }

    @Override
    public MODEL setKeyBinding(KeyBinding keyBinding) {
        getEntity().setKeyBinding(SimpleValueObject.nullSafe(keyBinding));
        //noinspection unchecked
        return (MODEL) this;
    }
}