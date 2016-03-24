package org.boatpos.common.repository.core.builder;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.repository.api.builder.DomainModelBuilder;
import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Version;
import org.boatpos.common.repository.core.model.DomainModelCore;

import java.lang.reflect.ParameterizedType;

public abstract class DomainModelBuilderCore<BUILDER extends DomainModelBuilder, MODEL extends DomainModel, MODELCORE extends DomainModelCore, ENTITY extends AbstractEntity>
        implements DomainModelBuilder<BUILDER, MODEL, ENTITY> {

    protected DomainId id;
    protected Version version;

    @Override
    public MODEL from(ENTITY entity) {
        try {
            //noinspection unchecked
            return (MODEL) getDomainModelCoreClass().getDeclaredConstructor(entity.getClass()).newInstance(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    protected Class<MODELCORE> getDomainModelCoreClass() {
        return (Class<MODELCORE>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
    }

    public BUILDER add(DomainId id) {
        this.id = id;
        //noinspection unchecked
        return (BUILDER) this;
    }

    public BUILDER add(Version version) {
        this.version = version;
        //noinspection unchecked
        return (BUILDER) this;
    }
}
