package org.boatpos.common.service.core;

import org.boatpos.common.repository.api.builder.MasterDataBuilderWithDto;
import org.boatpos.common.repository.api.model.MasterDataWithDto;
import org.boatpos.common.repository.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.service.api.EnabledState;
import org.boatpos.common.service.api.MasterDataService;
import org.boatpos.common.service.api.bean.AbstractMasterDataBean;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public abstract class MasterDataServiceCore<DTO extends AbstractMasterDataBean> implements MasterDataService<DTO> {

    @Inject
    private ModelDtoConverter modelDtoConverter;

    @Inject
    private MasterDataHelper masterDataHelper;

    @Override
    public Optional<DTO> getById(Long id) {
        //noinspection unchecked
        return modelDtoConverter.convert(getRepository().loadBy(new DomainId(id)));
    }

    @Override
    public List<DTO> getAll() {
        //noinspection unchecked
        return modelDtoConverter.convert(masterDataHelper.loadAll(getRepository(), EnabledState.All));
    }

    @Override
    public List<DTO> getAll(EnabledState enabledState) {
        //noinspection unchecked
        return modelDtoConverter.convert(masterDataHelper.loadAll(getRepository(), enabledState));
    }

    @Override
    public void enable(Long id) {
        masterDataHelper.enable(getRepository(), new DomainId(id));
    }

    @Override
    public void disable(Long id) {
        masterDataHelper.disable(getRepository(), new DomainId(id));
    }

    @Override
    public DTO save(DTO bean) {
        //noinspection unchecked
        return (DTO) ((MasterDataWithDto) ((MasterDataBuilderWithDto) getRepository().builder()).from(bean).persist()).asDto();
    }

    @Override
    public DTO update(DTO bean) {
        return save(bean);
    }

    protected abstract MasterDataRepositoryWithDto getRepository();
}
