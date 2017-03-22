package org.boatpos.service.core;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.service.core.MasterDataHelper;
import org.boatpos.common.service.core.ModelDtoConverter;
import org.boatpos.domain.api.repository.CommitmentRepository;
import org.boatpos.domain.api.values.Name;
import org.boatpos.service.api.CommitmentService;
import org.boatpos.common.service.api.EnabledState;
import org.boatpos.service.api.bean.CommitmentBean;
import org.boatpos.common.util.log.SLF4J;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class CommitmentServiceCore implements CommitmentService {

    @Inject
    @SLF4J
    private Logger log;

    @Inject
    private CommitmentRepository commitmentRepository;

    @Inject
    private ModelDtoConverter modelDtoConverter;

    @Inject
    private MasterDataHelper masterDataHelper;

    @Override
    public List<CommitmentBean> getAll() {
        return modelDtoConverter.convert(commitmentRepository.loadAll());
    }

    @Override
    public List<CommitmentBean> getAll(EnabledState enabledState) {
        return modelDtoConverter.convert(masterDataHelper.loadAll(commitmentRepository, enabledState));
    }

    @Override
    public Optional<CommitmentBean> getById(Long id) {
        return modelDtoConverter.convert(commitmentRepository.loadBy(new DomainId(id)));
    }

    @Override
    public Optional<CommitmentBean> getByName(String name) {
        return modelDtoConverter.convert(commitmentRepository.loadBy(new Name(name)));
    }

    @Override
    public CommitmentBean save(CommitmentBean commitmentBean) {
        return commitmentRepository.builder().from(commitmentBean).persist().asDto();
    }

    @Override
    public CommitmentBean update(CommitmentBean commitmentBean) {
        return commitmentRepository.builder().from(commitmentBean).persist().asDto();
    }


    @Override
    public void enable(Long id) {
        masterDataHelper.enable(commitmentRepository, new DomainId(id));
    }

    @Override
    public void disable(Long id) {
        masterDataHelper.disable(commitmentRepository, new DomainId(id));
    }
}
