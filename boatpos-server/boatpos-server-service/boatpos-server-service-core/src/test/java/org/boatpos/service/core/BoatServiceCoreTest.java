package org.boatpos.service.core;

import org.boatpos.service.api.BoatService;
import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.MasterDataService;
import org.boatpos.service.api.bean.BoatBean;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BoatServiceCoreTest extends AbstractMasterDataServiceTest<BoatBean> {

    @Inject
    private BoatService boatService;

    @Test
    @Transactional
    public void testGetById() throws Exception {
        Long id = boatService.getByShortName("E").get().getId();
        assertEquals("E", boatService.getById(id).get().getShortName());
        assertFalse(boatService.getById(999L).isPresent());
    }

    @Test
    @Transactional
    public void testGetByName() throws Exception {
        assertEquals("E", boatService.getByName("E-Boot").get().getShortName());
        assertFalse(boatService.getByName("xxx").isPresent());
    }

    @Test
    @Transactional
    public void testGetByShortName() throws Exception {
        assertEquals("E-Boot", boatService.getByShortName("E").get().getName());
        assertFalse(boatService.getByShortName("xxx").isPresent());
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
        boatService.save(boatBean);
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        BoatBean boatBean = boatService.getByShortName("E").get();
        boatBean.setName("EBOOT");
        boatService.update(boatBean);
        assertEquals(2, boatService.getByName("EBOOT").get().getVersion().intValue());
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
        return boatService.getByShortName("P").get().getId();
    }

    @Override
    protected Long idToDisable() {
        return boatService.getByShortName("E").get().getId();
    }
}