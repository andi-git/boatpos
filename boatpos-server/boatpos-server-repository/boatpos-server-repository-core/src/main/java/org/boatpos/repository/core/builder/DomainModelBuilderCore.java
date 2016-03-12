package org.boatpos.repository.core.builder;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.repository.api.builder.DomainModelBuilder;
import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Version;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;
import org.boatpos.repository.core.model.DomainModelCore;

import java.lang.reflect.ParameterizedType;

public abstract class DomainModelBuilderCore<BUILDER extends DomainModelBuilder, MODEL extends DomainModel, MODELCORE extends DomainModelCore, ENTITY extends AbstractEntity, DTO extends AbstractBeanBasedOnEntity>
        implements DomainModelBuilder<BUILDER, MODEL, ENTITY, DTO> {

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

    @Override
    public MODEL from(DTO dto) {
        try {
            //noinspection unchecked
            return (MODEL) getDomainModelCoreClass().getDeclaredConstructor(dto.getClass()).newInstance(dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<MODELCORE> getDomainModelCoreClass() {
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
