package org.boatpos.service.core.util;

import org.boatpos.repository.api.model.DomainModel;
import org.boatpos.service.api.bean.AbstractBeanBasedOnEntity;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Helper for CRUD-operation with some generic methods.
 */
@Dependent
public class CrudHelper {

    /**
     * Convert a {@link List} of {@link MODEL}s to a {@link List} of {@link DTO}s.
     *
     * @param models  the {@link MODEL}s to convert
     * @param <MODEL> the concrete type of the {@link DomainModel}
     * @param <DTO>   the concrete type of te {@link AbstractBeanBasedOnEntity}
     * @return a {@link List} of {@link DTO}s converted from the {@link List} of {@link MODEL}s
     */
    public <MODEL extends DomainModel, DTO extends AbstractBeanBasedOnEntity> List<DTO> convert(List<MODEL> models) {
        checkNotNull(models, "'model-list' must not be null");
        //noinspection unchecked
        return models.stream().map(model -> (DTO) model.asDto()).collect(Collectors.toList());
    }

    public <MODEL extends DomainModel, DTO extends AbstractBeanBasedOnEntity> Optional<DTO> convert(Optional<MODEL> model) {
        checkNotNull(model, "'model' must not be null");
        if (model.isPresent()) {
            //noinspection unchecked
            return Optional.of((DTO) model.get().asDto());
        } else {
            return Optional.empty();
        }
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
//    public <ENTITY extends AbstractEntity, DTO extends AbstractBeanBasedOnEntity> Optional<DTO> update(DTO dto, GenericDao<ENTITY> dao, Mapping<ENTITY, DTO> mapping, Runnable logError) {
//        Optional<ENTITY> entityOptional = dao.getById(dto.getId());
//        if (entityOptional.isPresent()) {
//            ENTITY entity = entityOptional.get();
//            mapping.mapDto(dto, entity);
//            entity = dao.update(entity);
//            return Optional.of(mapping.mapEntity(entity));
//        } else {
//            logError.run();
//            return Optional.empty();
//        }
//    }
}
