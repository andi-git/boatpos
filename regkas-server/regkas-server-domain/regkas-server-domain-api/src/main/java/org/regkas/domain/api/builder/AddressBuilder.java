package org.regkas.domain.api.builder;

import org.boatpos.common.domain.api.builder.DomainModelBuilder;
import org.regkas.domain.api.model.Address;
import org.regkas.domain.api.values.ZIP;
import org.regkas.model.AddressEntity;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.values.City;
import org.regkas.domain.api.values.Country;
import org.regkas.domain.api.values.Street;

/**
 * Builder for {@link CashBox}.
 */
public interface AddressBuilder extends DomainModelBuilder<AddressBuilder, Address, AddressEntity> {

    AddressBuilder add(Street street);

    AddressBuilder add(ZIP zip);

    AddressBuilder add(City city);

    AddressBuilder add(Country country);
}
