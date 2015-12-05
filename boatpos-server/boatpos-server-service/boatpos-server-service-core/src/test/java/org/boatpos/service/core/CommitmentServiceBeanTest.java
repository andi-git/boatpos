package org.boatpos.service.core;

import org.boatpos.service.api.CommitmentService;
import org.boatpos.service.api.bean.CommitmentBean;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CommitmentServiceBeanTest extends EntityManagerProviderForBoatpos {

    @EJB
    private CommitmentService commitmentService;

    @Test
    @Transactional
    public void testGetAll() throws Exception {
        assertEquals(5, commitmentService.getAll().size());
    }

    @Test
    @Transactional
    public void testGetById() throws Exception {
        assertEquals("Ausweis", commitmentService.getById(4L).getName());
    }

    @Test
    @Transactional
    public void testGetByName() throws Exception {
        assertEquals(4L, commitmentService.getByName("Ausweis").getId().longValue());
    }

    @Test
    @Transactional
    public void testSave() throws Exception {
        assertEquals(5, commitmentService.getAll().size());
        commitmentService.save(new CommitmentBean(null, null, "Pass", true, 10));
        assertEquals(6, commitmentService.getAll().size());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        CommitmentBean commitmentBean = commitmentService.getByName("Ausweis");
        commitmentBean.setName("AUSWEIS!");
        commitmentService.update(commitmentBean);
        assertEquals(2, commitmentService.getByName("AUSWEIS!").getVersion().intValue());
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        assertEquals(5, commitmentService.getAll().size());
        System.out.println(commitmentService.getAll().size());
        System.out.println(commitmentService.getAll());
        commitmentService.delete(4L);
        System.out.println(commitmentService.getAll().size());
        System.out.println(commitmentService.getAll());
        assertEquals(4, commitmentService.getAll().size());
    }
}
