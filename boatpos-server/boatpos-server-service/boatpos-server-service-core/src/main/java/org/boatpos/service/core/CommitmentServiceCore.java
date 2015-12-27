package org.boatpos.service.core;

import org.boatpos.repository.api.repository.CommitmentRepository;
import org.boatpos.repository.api.values.DomainId;
import org.boatpos.repository.api.values.Name;
import org.boatpos.service.api.CommitmentService;
import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.bean.CommitmentBean;
import org.boatpos.service.core.util.CrudHelper;
import org.boatpos.service.core.util.MasterDataHelper;
import org.boatpos.util.log.SLF4J;
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
    private CrudHelper crudHelper;

    @Inject
    private MasterDataHelper masterDataHelper;

    @Override
    public List<CommitmentBean> getAll() {
        return crudHelper.convert(commitmentRepository.loadAll());
    }

    @Override
    public List<CommitmentBean> getAll(EnabledState enabledState) {
        return crudHelper.convert(masterDataHelper.loadAll(commitmentRepository, enabledState));
    }

    @Override
    public Optional<CommitmentBean> getById(Long id) {
        return crudHelper.convert(commitmentRepository.loadBy(new DomainId(id)));
    }

    @Override
    public Optional<CommitmentBean> getByName(String name) {
        return crudHelper.convert(commitmentRepository.loadBy(new Name(name)));
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
