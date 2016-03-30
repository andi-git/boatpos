package org.boatpos.common.service.core;

import org.boatpos.common.service.api.EnabledState;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class MasterDataServiceCoreTest {

    @Inject
    private FooServiceCore fooServiceCore;

    @Test
    public void getById() throws Exception {
        assertEquals(1L, fooServiceCore.getById(1L).get().getId().longValue());
    }

    @Test
    public void getAll() throws Exception {
        assertEquals(2, fooServiceCore.getAll().size());
    }

    @Test
    public void getAllEnabledDisabled() throws Exception {
        assertEquals(2, fooServiceCore.getAll(EnabledState.All).size());
        assertEquals(1, fooServiceCore.getAll(EnabledState.Enabled).size());
        assertEquals(1, fooServiceCore.getAll(EnabledState.Disabled).size());

    }

    @Test
    public void enable() throws Exception {
        fooServiceCore.enable(1L);
    }

    @Test
    public void disable() throws Exception {
        fooServiceCore.disable(1L);
    }

    @Test
    public void save() throws Exception {
        assertNotNull(fooServiceCore.save(new FooBean()));
    }

    @Test
    public void update() throws Exception {
        assertNotNull(fooServiceCore.update(new FooBean()));
    }
}