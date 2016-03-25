package org.regkas.repository.core.repository;

import org.boatpos.common.repository.core.respository.DomainModelRepositoryCore;
import org.regkas.model.AddressEntity;
import org.regkas.repository.api.builder.AddressBuilder;
import org.regkas.repository.api.model.Address;
import org.regkas.repository.api.repository.AddressRepository;
import org.regkas.repository.core.builder.AddressBuilderCore;
import org.regkas.repository.core.model.AddressCore;

import javax.enterprise.context.Dependent;

@Dependent
public class AddressRepositoryCore extends DomainModelRepositoryCore<Address, AddressCore, AddressEntity> implements AddressRepository {

    @Override
    public AddressBuilder builder() {
        return new AddressBuilderCore();
    }
}
