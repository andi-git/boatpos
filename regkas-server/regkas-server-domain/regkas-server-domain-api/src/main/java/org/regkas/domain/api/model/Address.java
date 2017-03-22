package org.regkas.domain.api.model;

import org.boatpos.common.domain.api.model.DomainModel;
import org.regkas.model.AddressEntity;
import org.regkas.domain.api.values.City;
import org.regkas.domain.api.values.Country;
import org.regkas.domain.api.values.Street;
import org.regkas.domain.api.values.ZIP;

/**
 * The domain model for a company.
 */
public interface Address extends DomainModel<Address, AddressEntity> {

    Street getStreet();

    Address setStreet(Street street);

    ZIP getZIP();

    Address setZIP(ZIP zip);

    City getCity();

    Address setCity(City city);

    Country getCountry();

    Address setCountry(Country country);
}
