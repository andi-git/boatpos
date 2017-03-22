package org.regkas.repository.api.repository;

import org.boatpos.common.repository.api.repository.DomainModelRepository;
import org.boatpos.common.repository.api.repository.MasterDataRepository;
import org.regkas.repository.api.builder.AddressBuilder;
import org.regkas.repository.api.builder.UserBuilder;
import org.regkas.repository.api.model.Address;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.values.Name;

import java.util.Optional;

/**
 * The repository for the {@link Address}.
 */
public interface AddressRepository extends DomainModelRepository<Address, AddressBuilder> {

}
