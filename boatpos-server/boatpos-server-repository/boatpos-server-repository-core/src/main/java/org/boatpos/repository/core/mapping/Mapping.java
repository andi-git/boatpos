package org.boatpos.repository.core.mapping;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.repository.api.BoatPosDB;
import org.boatpos.service.api.bean.AbstractBeanBasedOnEntity;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Mapping<ENTITY extends AbstractEntity, DTO extends AbstractBeanBasedOnEntity> {

    @Inject
    private MappingHelper mappingHelper;

    @Inject
    @BoatPosDB
    private EntityManager entityManager;

    public DTO mapEntity(ENTITY entity) {
        return mappingHelper.map(entity, getMappedDtoClass());
    }

    public Optional<DTO> mapEntity(Optional<ENTITY> entity) {
        return entity.isPresent() ? Optional.of(mapEntity(entity.get())) : Optional.empty();
    }

    public void mapEntity(ENTITY entity, DTO dto) {
        mappingHelper.map(entity, dto);
    }

    public ENTITY mapDto(DTO dto) {
        ENTITY entity;
        if (dto.getId() != null) {
            entity = entityManager.find(getMappedEntityClass(), dto.getId());
            if (entity != null) {
                mapDto(dto, entity);
                entityManager.persist(entity);
            } else {
                entity = mappingHelper.map(dto, getMappedEntityClass());
            }
        } else {
            entity = mappingHelper.map(dto, getMappedEntityClass());
        }
        return entity;
    }

    public Optional<ENTITY> mapDto(Optional<DTO> dto) {
        return dto.isPresent() ? Optional.of(mapDto(dto.get())) : Optional.empty();
    }

    public void mapDto(DTO dto, ENTITY entity) {
        mappingHelper.map(dto, entity);
    }

    protected Class<DTO> getMappedDtoClass() {
        //noinspection unchecked
        return (Class<DTO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected Class<ENTITY> getMappedEntityClass() {
        //noinspection unchecked
        return (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public List<DTO> mapEntities(List<ENTITY> entities) {
        return entities.stream().map(this::mapEntity).collect(Collectors.toList());
    }

    public Set<DTO> mapEntities(Set<ENTITY> entities) {
        return new HashSet<>(mapEntities(new ArrayList<>(entities)));
    }

    public List<ENTITY> mapDtos(List<DTO> dtos) {
        return dtos.stream().map(this::mapDto).collect(Collectors.toList());
    }

    public Set<ENTITY> mapDtos(Set<DTO> dtos) {
        return new HashSet<>(mapDtos(new ArrayList<>(dtos)));
    }
}
