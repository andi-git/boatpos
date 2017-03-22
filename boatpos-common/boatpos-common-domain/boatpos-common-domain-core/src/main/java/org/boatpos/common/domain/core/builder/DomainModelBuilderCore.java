package org.boatpos.common.domain.core.builder;

import java.lang.reflect.ParameterizedType;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.domain.api.builder.DomainModelBuilder;
import org.boatpos.common.domain.api.model.DomainModel;
import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Version;
import org.boatpos.common.domain.core.model.DomainModelCore;

public abstract class DomainModelBuilderCore<BUILDER extends DomainModelBuilder, MODEL extends DomainModel, MODELCORE extends DomainModelCore, ENTITY extends AbstractEntity>
        extends
            ModelBuilderCore<MODEL>
        implements
            DomainModelBuilder<BUILDER, MODEL, ENTITY> {

    protected DomainId id;
    protected Version version;

    @Override
    public MODEL from(ENTITY entity) {
        try {
            // noinspection unchecked
            return (MODEL) getDomainModelCoreClass().getDeclaredConstructor(entity.getClass()).newInstance(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    protected Class<MODELCORE> getDomainModelCoreClass() {
        return (Class<MODELCORE>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
    }

    @SuppressWarnings("unchecked")
    protected Class<ENTITY> getEntityClass() {
        return (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];
    }

    @Override
    public BUILDER add(DomainId id) {
        this.id = id;
        // noinspection unchecked
        return (BUILDER) this;
    }

    @Override
    public BUILDER add(Version version) {
        this.version = version;
        // noinspection unchecked
        return (BUILDER) this;
    }
}
