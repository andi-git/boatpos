package org.regkas.repository.api.builder;

import org.boatpos.common.repository.api.builder.DomainModelBuilder;
import org.regkas.model.AddressEntity;
import org.regkas.repository.api.model.Address;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.values.City;
import org.regkas.repository.api.values.Country;
import org.regkas.repository.api.values.Street;
import org.regkas.repository.api.values.ZIP;

/**
 * Builder for {@link CashBox}.
 */
public interface AddressBuilder extends DomainModelBuilder<AddressBuilder, Address, AddressEntity> {

    AddressBuilder add(Street street);

    AddressBuilder add(ZIP zip);

    AddressBuilder add(City city);

    AddressBuilder add(Country country);
}
