package org.boatpos.service.core;

import org.boatpos.service.api.CommitmentService;
import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.MasterDataService;
import org.boatpos.service.api.bean.CommitmentBean;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class CommitmentServiceCoreTest extends AbstractMasterDataServiceTest<CommitmentBean> {

    @Inject
    private CommitmentService commitmentService;

    @Override
    protected MasterDataService<CommitmentBean> service() {
        return commitmentService;
    }

    @Override
    protected int countAll() {
        return 6;
    }

    @Override
    protected int countAllEnabled() {
        return 5;
    }

    @Override
    protected int countAllDisabled() {
        return 1;
    }

    @Override
    protected Long idToEnable() {
        return commitmentService.getByName("Kinderwagen").get().getId();
    }

    @Override
    protected Long idToDisable() {
        return commitmentService.getByName("Ausweis").get().getId();
    }

    @Test
    @Transactional
    public void testGetById() throws Exception {
        assertEquals("Ausweis", commitmentService.getById(4L).get().getName());
    }

    @Test
    @Transactional
    public void testGetByName() throws Exception {
        assertEquals(4L, commitmentService.getByName("Ausweis").get().getId().longValue());
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        assertEquals(5, commitmentService.getAll(EnabledState.Enabled).size());
        commitmentService.save(new CommitmentBean(null, null, "Pass", true, 10, true));
        assertEquals(6, commitmentService.getAll(EnabledState.Enabled).size());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        CommitmentBean commitmentBean = commitmentService.getByName("Ausweis").get();
        commitmentBean.setName("AUSWEIS!");
        commitmentService.update(commitmentBean);
        assertEquals(2, commitmentService.getByName("AUSWEIS!").get().getVersion().intValue());
    }
}
