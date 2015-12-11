package org.boatpos.service.core;

import org.boatpos.model.Boat;
import org.boatpos.service.api.BoatService;
import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.MasterDataService;
import org.boatpos.service.api.bean.BoatBean;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.PersistenceException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class BoatServiceBeanTest extends AbstractMasterDataServiceTest<BoatBean> {

    @EJB
    private BoatService boatService;

    @Test
    @Transactional
    public void testGetById() throws Exception {
        Long id = boatService.getByShortName("E").getId();
        assertEquals("E", boatService.getById(id).getShortName());
        assertNull(boatService.getById(999L));
    }

    @Test
    @Transactional
    public void testGetByName() throws Exception {
        assertEquals("E", boatService.getByName("E-Boot").getShortName());
        assertNull(boatService.getByName("xxx"));
    }

    @Test
    @Transactional
    public void testGetByShortName() throws Exception {
        assertEquals("E-Boot", boatService.getByShortName("E").getName());
        assertNull(boatService.getByShortName("xxx"));
    }

    @Test
    @Transactional
    public void testSave() throws Throwable {
        assertEquals(5, boatService.getAll(EnabledState.Enabled).size());
        BoatBean boatBean = new BoatBean(null, null, "TG", "Tretboot groß", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 10, 5, true);
        boatService.save(boatBean);
        assertEquals(6, boatService.getAll(EnabledState.Enabled).size());
    }

    @Test(expected = PersistenceException.class)
    @Transactional
    public void testSaveWithException() throws Throwable {
        assertEquals(5, boatService.getAll(EnabledState.Enabled).size());
        BoatBean boatBean = new BoatBean(-1L, null, "xxxx", "Tretboot groß", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 10, 5, true);
        try {
            boatService.save(boatBean);
        } catch (Exception e) {
            throw e.getCause();
        }
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        BoatBean boatBean = boatService.getByShortName("E");
        boatBean.setName("EBOOT");
        boatService.update(boatBean);
        assertEquals(2, boatService.getByName("EBOOT").getVersion().intValue());
        assertEquals(5, boatService.getAll(EnabledState.Enabled).size());

        boatBean = new BoatBean();
        boatBean.setId(999L);
        assertNull(boatService.update(boatBean));
    }

    @Override
    protected MasterDataService<BoatBean> service() {
        return boatService;
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
        return boatService.getByShortName("P").getId();
    }

    @Override
    protected Long idToDisable() {
        return boatService.getByShortName("E").getId();
    }
}
