package org.boatpos.service.core.util;

import org.boatpos.dao.api.GenericDao;
import org.boatpos.model.AbstractEntity;
import org.boatpos.model.AbstractMasterDataEntity;
import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.bean.AbstractBeanBasedOnEntity;
import org.boatpos.service.core.mapping.Mapping;

import javax.enterprise.context.Dependent;
import java.util.Optional;

/**
 * Helper for CRUD-operation with some generic methods.
 */
@Dependent
public class CrudHelper {

    /**
     * Map the {@link ENTITY} of the {@link Optional} to the {@link DTO} of return null.
     *
     * @param entity   the {@link ENTITY}
     * @param mapping  the mapping-component between {@link ENTITY} and {@link DTO}
     * @param logError the error-strategy if the {@link Optional} is not set (e.g. log a messages)
     * @param <ENTITY> the type of the entity
     * @param <DTO>    the type of the dto
     * @return the {@link DTO} (mapped from the {@link ENTITY}) or {@code null}
     */
    public <ENTITY extends AbstractEntity, DTO extends AbstractBeanBasedOnEntity> DTO getOrNull(Optional<ENTITY> entity, Mapping<ENTITY, DTO> mapping, Runnable logError) {
        DTO dto = null;
        if (entity.isPresent()) {
            dto = mapping.mapEntity(entity.get());
        } else {
            logError.run();
        }
        return dto;
    }

    /**
     * Get the {@link DTO} of the {@link Optional} or {@code null}.
     *
     * @param dtoOptional the {@link Optional} containing the {@link DTO}
     * @param logError    the error-strategy if the {@link Optional} is not set (e.g. log a messages)
     * @param <DTO>       the type of the dto
     * @return the {@link DTO} or {@code null}
     */
    public <DTO extends AbstractBeanBasedOnEntity> DTO getOrNull(Optional<DTO> dtoOptional, Runnable logError) {
        DTO dto = null;
        if (dtoOptional.isPresent()) {
            dto = dtoOptional.get();
        } else {
            logError.run();
        }
        return dto;
    }

    /**
     * Update a {@link DTO} in the database. Internally, the {@link DTO} must be mapped to an {@link ENTITY}.
     *
     * @param dto      the {@link DTO} to update
     * @param dao      the {@link GenericDao} to perform the update-operation
     * @param mapping  the {@link Mapping} to map between the {@link DTO} and the {@link ENTITY}
     * @param logError the error-strategy if the {@link ENTITY} to update was not found (e.g. log a messages)
     * @param <ENTITY> the type of the entity
     * @param <DTO>    the type of the dto
     * @return an {@link Optional} of the {@link DTO}. If the update was not successful, the {@link Optional} is empty
     */
    public <ENTITY extends AbstractEntity, DTO extends AbstractBeanBasedOnEntity> Optional<DTO> update(DTO dto, GenericDao<ENTITY> dao, Mapping<ENTITY, DTO> mapping, Runnable logError) {
        Optional<ENTITY> entityOptional = dao.getById(dto.getId());
        if (entityOptional.isPresent()) {
            ENTITY entity = entityOptional.get();
            mapping.mapDto(dto, entity);
            entity = dao.update(entity);
            return Optional.of(mapping.mapEntity(entity));
        } else {
            logError.run();
            return Optional.empty();
        }
    }
}
