package org.boatpos.service.core;

import org.boatpos.dao.api.CommitmentDao;
import org.boatpos.model.Commitment;
import org.boatpos.service.api.CommitmentService;
import org.boatpos.service.api.bean.CommitmentBean;
import org.boatpos.service.core.mapping.CommitmentMapping;
import org.boatpos.service.core.util.GenericCrudHelper;
import org.boatpos.util.log.SLF4J;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class CommitmentServiceBean implements CommitmentService {

    @Inject
    @SLF4J
    private Logger log;

    @Inject
    private CommitmentDao commitmentDao;

    @Inject
    private CommitmentMapping commitmentMapping;

    @Inject
    private GenericCrudHelper crudHelper;

    @Override
    public List<CommitmentBean> getAll() {
        return commitmentMapping.mapEntities(commitmentDao.getAll());
    }

    @Override
    public CommitmentBean getById(Long id) {
        return crudHelper.getOrNull(commitmentDao.getById(id), commitmentMapping, () -> log.error("no {} available with id {}", Commitment.class.getName(), id));
    }

    @Override
    public CommitmentBean getByName(String name) {
        return crudHelper.getOrNull(commitmentDao.getByName(name), commitmentMapping, () -> log.error("no {} available with name {}", Commitment.class.getName(), name));
    }

    @Override
    public CommitmentBean save(CommitmentBean commitmentBean) {
        return commitmentMapping.mapEntity(commitmentDao.save(commitmentMapping.mapDto(commitmentBean)));
    }

    @Override
    public CommitmentBean update(CommitmentBean commitmentBean) {
        Optional<CommitmentBean> updatedDto = crudHelper.update(commitmentBean, commitmentDao, commitmentMapping, () -> log.error("unable to update {} {}", Commitment.class.getName(), commitmentBean));
        return crudHelper.getOrNull(updatedDto, () -> {});
    }

    @Override
    public void delete(Long id) {
        commitmentDao.delete(id);
    }
}
