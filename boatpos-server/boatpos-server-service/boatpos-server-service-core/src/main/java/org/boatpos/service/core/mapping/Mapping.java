package org.boatpos.service.core.mapping;

import org.boatpos.model.AbstractEntity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Mapping<ENTITY extends AbstractEntity, DTO> {

    @Inject
    private MappingHelper mappingHelper;

    public DTO mapEntity(ENTITY entity) {
        return mappingHelper.map(entity, getMappedDtoClass());
    }

    public ENTITY mapDto(DTO dto) {
        return mappingHelper.map(dto, getMappedEntityClass());
    }

    protected abstract Class<DTO> getMappedDtoClass();

    protected abstract Class<ENTITY> getMappedEntityClass();

    public List<DTO> mapEntities(List<ENTITY> entities) {
        List<DTO> dtos = new ArrayList<>();
        for (ENTITY entity : entities) {
            dtos.add(mapEntity(entity));
        }
        return dtos;
    }

    public Set<DTO> mapEntities(Set<ENTITY> entities) {
        return new HashSet<DTO>(mapEntities(new ArrayList<ENTITY>(entities)));
    }

    public List<ENTITY> mapDtos(List<DTO> dtos) {
        List<ENTITY> entities = new ArrayList<>();
        for (DTO dto : dtos) {
            entities.add(mapDto(dto));
        }
        return entities;
    }

    public Set<ENTITY> mapDtos(Set<DTO> dtos) {
        return new HashSet<ENTITY>(mapDtos(new ArrayList<DTO>(dtos)));
    }
}
