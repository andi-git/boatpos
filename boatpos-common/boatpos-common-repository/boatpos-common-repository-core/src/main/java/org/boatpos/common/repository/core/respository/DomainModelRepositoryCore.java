package org.boatpos.common.repository.core.respository;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.repository.api.builder.DomainModelBuilder;
import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.repository.DomainModelRepository;
import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.core.JPAHelper;
import org.boatpos.common.repository.core.builder.DomainModelBuilderCore;
import org.boatpos.common.repository.core.model.DomainModelCore;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class DomainModelRepositoryCore<MODEL extends DomainModel, MODELCORE extends DomainModelCore, ENTITY extends AbstractEntity, BUILDER extends DomainModelBuilder, BUILDERCORE extends DomainModelBuilderCore>
        implements DomainModelRepository<MODEL, BUILDER> {

    @Inject
    private JPAHelper jpaHelper;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Override
    public BUILDER builder() {
        try {
            //noinspection unchecked
            return (BUILDER) getBuilderCoreType().getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("unable to create builder, type: " + getBuilderCoreType(), e);
        }
    }

    @Override
    public Optional<MODEL> loadBy(DomainId id) {
        log.debug("search {} via id: {}", getTypeEntity().getName(), id);
        Optional<MODEL> result = Optional.empty();
        if (id != null && id.get() != null) {
            ENTITY entity = jpaHelper.getEntityManager().find(getTypeEntity(), id.get());
            if (entity != null) {
                MODEL domainModel;
                try {
                    //noinspection unchecked
                    domainModel = (MODEL) getTypeDomainModelCore().getConstructor(getTypeEntity()).newInstance(entity);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                result = Optional.of(domainModel);
            }
        }
        return result;
    }

    protected Class<MODELCORE> getTypeDomainModelCore() {
        //noinspection unchecked
        return (Class<MODELCORE>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected Class<ENTITY> getTypeEntity() {
        //noinspection unchecked
        return (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
    }

    protected Class<BUILDERCORE> getBuilderCoreType() {
        //noinspection unchecked
        return (Class<BUILDERCORE>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[4];
    }

    protected List<MODEL> loadAll(String namedQuery, Function<ENTITY, MODEL> mapper) {
        return loadAll(namedQuery, mapper, (t) -> {
        });
    }

    protected List<MODEL> loadAll(String namedQuery, Function<ENTITY, MODEL> mapper, Consumer<TypedQuery> addParameter) {
        return loadAll(namedQuery, mapper, addParameter, getTypeEntity());
    }

    protected <E extends AbstractEntity> List<MODEL> loadAll(String namedQuery, Function<E, MODEL> mapper, Consumer<TypedQuery> addParameter, Class<E> typeEntity) {
        TypedQuery<E> query = jpaHelper().createNamedQuery(namedQuery, typeEntity);
        addParameter.accept(query);
        return query.getResultList().stream().map(mapper::apply).collect(Collectors.toList());
    }

    protected Optional<MODEL> load(String namedQuery) {
        return loadByParameter(namedQuery, (query) -> {}, getTypeEntity(), getTypeEntity());
    }

    protected Optional<MODEL> loadByParameter(String namedQuery, Consumer<TypedQuery> addParameter) {
        return loadByParameter(namedQuery, addParameter, getTypeEntity(), getTypeEntity());
    }

    protected <E extends AbstractEntity, CONCRETE extends AbstractEntity> Optional<MODEL> loadByParameter(String namedQuery, Consumer<TypedQuery> addParameter, Class<E> typeQueryEntity, Class<CONCRETE> typeConcreteEntity) {
        TypedQuery<E> query = jpaHelper().createNamedQuery(namedQuery, typeQueryEntity);
        addParameter.accept(query);
        Optional<E> singleResult = jpaHelper.getSingleResult(query.getResultList());
        if (singleResult.isPresent()) {
            try {
                //noinspection unchecked
                return Optional.of((MODEL) getTypeDomainModelCore().getConstructor(typeConcreteEntity).newInstance(singleResult.get()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }

    protected JPAHelper jpaHelper() {
        return jpaHelper;
    }
}
