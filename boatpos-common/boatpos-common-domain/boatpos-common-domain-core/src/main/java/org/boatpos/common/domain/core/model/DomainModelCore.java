package org.boatpos.common.domain.core.model;

import java.lang.reflect.ParameterizedType;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.domain.api.model.DomainModel;
import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Version;
import org.boatpos.common.util.log.LogWrapper;

public abstract class DomainModelCore<MODEL extends DomainModel, ENTITY extends AbstractEntity> extends ModelCore<MODEL, ENTITY>
        implements
            DomainModel<MODEL, ENTITY> {

    private final LogWrapper log;

    public DomainModelCore(DomainId id, Version version) {
        this();
        setId(id);
        setVersion(version);
    }

    public DomainModelCore() {
        super();
        // noinspection unchecked
        this.log = new LogWrapper((Class<MODEL>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public DomainModelCore(ENTITY abstractEntity) {
        super(abstractEntity);
        // noinspection unchecked
        this.log = new LogWrapper((Class<MODEL>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public MODEL persist() {
        return persistInternal((entity, jpaHelper) -> {
            if (getId() == null || getId().get() == null) {
                log.debug("persist {}: {}", getTypeEntity().getName(), getEntity());
                getJpaHelper().getEntityManager().persist(getEntity());
            } else {
                log.debug("merge {}: {}", getTypeEntity().getName(), getEntity());
                setEntity(getJpaHelper().getEntityManager().merge(getEntity()));
            }
        });
    }

    @Override
    public DomainId getId() {
        return new DomainId(getEntity().getId());
    }

    private MODEL setId(DomainId id) {
        getEntity().setId(id == null ? null : id.get());
        // noinspection unchecked
        return (MODEL) this;
    }

    @Override
    public Version getVersion() {
        return new Version(getEntity().getVersion());
    }

    private MODEL setVersion(Version version) {
        getEntity().setVersion(version == null ? null : version.get());
        // noinspection unchecked
        return (MODEL) this;
    }
}
