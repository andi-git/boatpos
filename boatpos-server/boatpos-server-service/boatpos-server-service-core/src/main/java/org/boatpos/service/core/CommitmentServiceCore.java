package org.boatpos.service.core;

import org.boatpos.dao.api.CommitmentDao;
import org.boatpos.model.Commitment;
import org.boatpos.service.api.CommitmentService;
import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.bean.CommitmentBean;
import org.boatpos.service.core.mapping.CommitmentMapping;
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
    private CommitmentDao commitmentDao;

    @Inject
    private CommitmentMapping commitmentMapping;

    @Inject
    private CrudHelper crudHelper;

    @Inject
    private MasterDataHelper masterDataHelper;

    @Override
    public List<CommitmentBean> getAll() {
        return getAll(EnabledState.All);
    }

    @Override
    public List<CommitmentBean> getAll(EnabledState enabledState) {
        return masterDataHelper.getAll(commitmentDao, commitmentMapping, enabledState);
    }

    @Override
    public Optional<CommitmentBean> getById(Long id) {
        return commitmentMapping.mapEntity(commitmentDao.getById(id));
    }

    @Override
    public Optional<CommitmentBean> getByName(String name) {
        return commitmentMapping.mapEntity(commitmentDao.getByName(name));
    }

    @Override
    public CommitmentBean save(CommitmentBean commitmentBean) {
        return commitmentMapping.mapEntity(commitmentDao.save(commitmentMapping.mapDto(commitmentBean)));
    }

    @Override
    public CommitmentBean update(CommitmentBean commitmentBean) {
        Optional<CommitmentBean> updatedDto = crudHelper.update(commitmentBean, commitmentDao, commitmentMapping, () -> log.error("unable to update {} {}", Commitment.class.getName(), commitmentBean));
        return crudHelper.getOrNull(updatedDto, () -> {
        });
    }


    @Override
    public void enable(Long id) {
        masterDataHelper.enable(id, commitmentDao);
    }

    @Override
    public void disable(Long id) {
        masterDataHelper.disable(id, commitmentDao);
    }

}
