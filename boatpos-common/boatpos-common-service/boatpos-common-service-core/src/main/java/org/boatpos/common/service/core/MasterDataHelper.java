package org.boatpos.common.service.core;

import org.boatpos.common.repository.api.builder.MasterDataBuilder;
import org.boatpos.common.repository.api.builder.MasterDataBuilderWithDto;
import org.boatpos.common.repository.api.model.MasterData;
import org.boatpos.common.repository.api.model.MasterDataWithDto;
import org.boatpos.common.repository.api.repository.MasterDataRepository;
import org.boatpos.common.repository.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Enabled;
import org.boatpos.common.service.api.EnabledState;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Component with some methods to handle master-data.
 */
@Dependent
public class MasterDataHelper {

    @Inject
    private ModelDtoConverter modelDtoConverter;

    /**
     * Load all/enabled/disabled {@link MasterData}s from the repository ordered by {@code priority}.
     *
     * @param repository   the {@link MasterDataRepository} to load from
     * @param enabledState the state of the {@link MODEL}s to load: {@link EnabledState#All} / {@link
     *                     EnabledState#Enabled} / {@link EnabledState#Disabled}
     * @param <MODEL>      the concrete type of the {@link MasterData}
     * @return an ordered {@link List} of all {@link MODEL}s from the repository with the specified {@link EnabledState}
     */
    public <MODEL extends MasterDataWithDto, BUILDER extends MasterDataBuilderWithDto> List<MODEL> loadAll(MasterDataRepositoryWithDto<MODEL, BUILDER> repository, EnabledState enabledState) {
        List<MODEL> entities = new ArrayList<>();
        if (repository != null) {
            if (EnabledState.Enabled == enabledState) {
                entities = repository.loadAll(Enabled.TRUE);
            } else if (EnabledState.Disabled == enabledState) {
                entities = repository.loadAll(Enabled.FALSE);
            } else {
                entities = repository.loadAll();
            }
        }
        return entities;
    }

    /**
     * Set the {@link MODEL} to {@code enabled}.
     *
     * @param repository the {@link MasterDataRepository} to load from
     * @param id         the {@link DomainId} of the {@link MODEL}
     * @param <MODEL>    the concrete type of the {@link MasterData}
     */
    public <MODEL extends MasterDataWithDto, BUILDER extends MasterDataBuilderWithDto> void enable(MasterDataRepositoryWithDto<MODEL, BUILDER> repository, DomainId id) {
        if (repository != null && id != null) {
            Optional<MODEL> model = repository.loadBy(id);
            if (model.isPresent()) {
                model.get().enable();
            }
        }
    }


    /**
     * Set the {@link MODEL} to {@code disabled}.
     *
     * @param repository the {@link MasterDataRepository} to load from
     * @param id         the {@link DomainId} of the {@link MODEL}
     * @param <MODEL>    the concrete type of the {@link MasterData}
     */
    public <MODEL extends MasterData, BUILDER extends MasterDataBuilder> void disable(MasterDataRepository<MODEL, BUILDER> repository, DomainId id) {
        if (repository != null && id != null) {
            Optional<MODEL> model = repository.loadBy(id);
            if (model.isPresent()) {
                model.get().disable();
            }
        }
    }
}
