package org.regkas.domain.api.repository;

import org.boatpos.common.domain.api.repository.DomainModelRepository;
import org.regkas.domain.api.model.Address;
import org.regkas.domain.api.builder.AddressBuilder;

/**
 * The repository for the {@link Address}.
 */
public interface AddressRepository extends DomainModelRepository<Address, AddressBuilder> {

}
