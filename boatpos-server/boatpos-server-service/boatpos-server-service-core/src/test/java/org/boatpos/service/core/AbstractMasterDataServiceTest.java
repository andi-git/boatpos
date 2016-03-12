package org.boatpos.service.core;

import org.boatpos.common.service.api.bean.AbstractMasterDataBean;
import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.MasterDataService;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractMasterDataServiceTest<DTO extends AbstractMasterDataBean> extends EntityManagerProviderForBoatpos {

    @Test
    @Transactional
    public void testGetAll() {
        assertEquals(countAll(), service().getAll().size());
        assertEquals(countAllEnabled(), service().getAll(EnabledState.Enabled).size());
        assertEquals(countAllDisabled(), service().getAll(EnabledState.Disabled).size());
        assertEquals(countAll(), service().getAll(EnabledState.All).size());
    }

    @Test
    @Transactional
    public void testEnable() {
        DTO dto = service().getById(idToEnable()).get();
        assertFalse(dto.isEnabled());
        service().enable(idToEnable());
        assertTrue(service().getById(idToEnable()).get().isEnabled());
    }

    @Test
    @Transactional
    public void testDisable() {
        DTO dto = service().getById(idToDisable()).get();
        assertTrue(dto.isEnabled());
        service().disable(idToDisable());
        assertFalse(service().getById(idToDisable()).get().isEnabled());
    }

    protected abstract MasterDataService<DTO> service();

    protected abstract int countAll();

    protected abstract int countAllEnabled();

    protected abstract int countAllDisabled();

    protected abstract Long idToEnable();

    protected abstract Long idToDisable();
}
