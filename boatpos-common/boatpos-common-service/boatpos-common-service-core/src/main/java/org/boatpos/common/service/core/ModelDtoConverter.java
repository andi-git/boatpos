package org.boatpos.common.service.core;

import org.boatpos.common.domain.api.model.DomainModel;
import org.boatpos.common.domain.api.model.DomainModelWithDto;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Helper for CRUD-operation with some generic methods.
 */
@Dependent
public class ModelDtoConverter {

    /**
     * Convert a {@link List} of {@link MODEL}s to a {@link List} of {@link DTO}s.
     *
     * @param models  the {@link MODEL}s to convert
     * @param <MODEL> the concrete type of the {@link DomainModel}
     * @param <DTO>   the concrete type of te {@link AbstractBeanBasedOnEntity}
     * @return a {@link List} of {@link DTO}s converted from the {@link List} of {@link MODEL}s
     */
    public <MODEL extends DomainModelWithDto, DTO extends AbstractBeanBasedOnEntity> List<DTO> convert(List<MODEL> models) {
        checkNotNull(models, "'model-list' must not be null");
        //noinspection unchecked
        return models.stream().map(model -> (DTO) model.asDto()).collect(Collectors.toList());
    }

    /**
     * Convert an {@link Optional} of {@link MODEL} to an {@link Optional} of {@link DTO}
     *
     * @param model   the {@link Optional} of the {@link MODEL} to convert
     * @param <MODEL> the concrete type of the {@link DomainModel}
     * @param <DTO>   the concrete type of te {@link AbstractBeanBasedOnEntity}
     * @return the converted {@link Optional} of the {@link DTO}
     */
    public <MODEL extends DomainModelWithDto, DTO extends AbstractBeanBasedOnEntity> Optional<DTO> convert(Optional<MODEL> model) {
        checkNotNull(model, "'model' must not be null");
        if (model.isPresent()) {
            //noinspection unchecked
            return Optional.of((DTO) model.get().asDto());
        } else {
            return Optional.empty();
        }
    }
}
