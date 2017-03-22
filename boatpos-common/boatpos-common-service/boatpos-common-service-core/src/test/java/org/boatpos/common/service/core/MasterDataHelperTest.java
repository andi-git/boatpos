package org.boatpos.common.service.core;

import com.google.common.collect.Lists;
import org.boatpos.common.domain.api.repository.MasterDataRepository;
import org.boatpos.common.domain.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.service.api.EnabledState;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Arquillian.class)
public class MasterDataHelperTest {

    @Inject
    private MasterDataHelper masterDataHelper;

    @Test
    public void testLoadAll() throws Exception {
        MasterDataRepositoryWithDto repository = mock(MasterDataRepositoryWithDto.class);
        when(repository.loadAll()).thenReturn(Lists.newArrayList(FooCore.createDummy1(), FooCore.createDummy2()));
        when(repository.loadAll(Enabled.TRUE)).thenReturn(Lists.newArrayList(FooCore.createDummy1()));
        when(repository.loadAll(Enabled.FALSE)).thenReturn(Lists.newArrayList(FooCore.createDummy2()));
        assertEquals(2, masterDataHelper.loadAll(repository, null).size());
        assertEquals(2, masterDataHelper.loadAll(repository, EnabledState.All).size());
        assertEquals(1L, ((FooCore) masterDataHelper.loadAll(repository, EnabledState.Enabled).get(0)).getId().get().longValue());
        assertEquals(2L, ((FooCore) masterDataHelper.loadAll(repository, EnabledState.Disabled).get(0)).getId().get().longValue());
    }

    @Test
    public void testEnable() throws Exception {
        MasterDataRepositoryWithDto repository = mock(MasterDataRepositoryWithDto.class);
        when(repository.loadBy(new DomainId(1L))).thenReturn(Optional.of(FooCore.createDummy1()));
        masterDataHelper.enable(repository, new DomainId(1L));
    }

    @Test
    public void testDisable() throws Exception {
        MasterDataRepository repository = mock(MasterDataRepository.class);
        when(repository.loadBy(new DomainId(1L))).thenReturn(Optional.of(FooCore.createDummy1()));
        masterDataHelper.disable(repository, new DomainId(1L));
    }
}