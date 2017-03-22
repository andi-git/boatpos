package org.boatpos.common.service.core;

import com.google.common.collect.Lists;
import org.boatpos.common.domain.api.builder.MasterDataBuilderWithDto;
import org.boatpos.common.domain.api.model.MasterDataWithDto;
import org.boatpos.common.domain.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

import javax.enterprise.context.RequestScoped;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
@RequestScoped
public class FooServiceCore extends MasterDataServiceCore<FooBean> {

    @Override
    protected MasterDataRepositoryWithDto getRepository() {
        MasterDataRepositoryWithDto repository = mock(MasterDataRepositoryWithDto.class);
        when(repository.loadBy(new DomainId(1L))).thenReturn(Optional.of(FooCore.createDummy1()));
        when(repository.loadAll()).thenReturn(Lists.newArrayList(FooCore.createDummy1(), FooCore.createDummy2()));
        when(repository.loadAll(Enabled.TRUE)).thenReturn(Lists.newArrayList(FooCore.createDummy1()));
        when(repository.loadAll(Enabled.FALSE)).thenReturn(Lists.newArrayList(FooCore.createDummy2()));

        MasterDataBuilderWithDto masterDataBuilder = mock(MasterDataBuilderWithDto.class);
        when(repository.builder()).thenReturn(masterDataBuilder);
        MasterDataWithDto masterData = mock(MasterDataWithDto.class);
        when(masterDataBuilder.from(any(AbstractBeanBasedOnEntity.class))).thenReturn(masterData);
        when(masterData.persist()).thenReturn(masterData);
        when(masterData.asDto()).thenReturn(new FooBean());

        return repository;
    }
}
