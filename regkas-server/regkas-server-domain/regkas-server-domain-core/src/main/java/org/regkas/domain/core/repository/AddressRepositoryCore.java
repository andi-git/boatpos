package org.regkas.domain.core.repository;

import org.boatpos.common.domain.core.respository.DomainModelRepositoryCore;
import org.regkas.domain.core.builder.AddressBuilderCore;
import org.regkas.domain.core.model.AddressCore;
import org.regkas.model.AddressEntity;
import org.regkas.domain.api.builder.AddressBuilder;
import org.regkas.domain.api.model.Address;
import org.regkas.domain.api.repository.AddressRepository;

import javax.enterprise.context.Dependent;

@Dependent
public class AddressRepositoryCore extends DomainModelRepositoryCore<Address, AddressCore, AddressEntity, AddressBuilder, AddressBuilderCore> implements AddressRepository {

}
