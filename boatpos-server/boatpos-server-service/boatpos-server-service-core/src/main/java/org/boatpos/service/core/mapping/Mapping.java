package org.boatpos.service.core.mapping;

import org.boatpos.model.AbstractEntity;
import org.boatpos.service.api.bean.AbstractBeanBasedOnEntity;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Mapping<ENTITY extends AbstractEntity, DTO extends AbstractBeanBasedOnEntity> {

    @Inject
    private MappingHelper mappingHelper;

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
        return mappingHelper.map(dto, getMappedEntityClass());
    }

    public Optional<ENTITY> mapDto(Optional<DTO> dto) {
        return dto.isPresent() ? Optional.of(mapDto(dto.get())) : Optional.empty();
    }

    public void mapDto(DTO dto, ENTITY entity) {
        mappingHelper.map(dto, entity);
    }

    protected abstract Class<DTO> getMappedDtoClass();

    protected abstract Class<ENTITY> getMappedEntityClass();

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
