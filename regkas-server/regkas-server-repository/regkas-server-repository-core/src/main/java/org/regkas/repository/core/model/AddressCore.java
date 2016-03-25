package org.regkas.repository.core.model;

import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.common.repository.api.values.Version;
import org.boatpos.common.repository.core.model.DomainModelCore;
import org.regkas.model.AddressEntity;
import org.regkas.repository.api.model.Address;
import org.regkas.repository.api.values.City;
import org.regkas.repository.api.values.Country;
import org.regkas.repository.api.values.Street;
import org.regkas.repository.api.values.ZIP;

import static com.google.common.base.Preconditions.checkNotNull;

public class AddressCore extends DomainModelCore<Address, AddressEntity> implements Address {

    public AddressCore(DomainId id,
                       Version version,
                       Street street,
                       ZIP zip,
                       City city,
                       Country country) {
        super(id, version);
        checkNotNull(street, "'street' must not be null");
        checkNotNull(zip, "'zip' must not be null");
        checkNotNull(city, "'city' must not be null");
        checkNotNull(country, "'country' must not be null");
        setStreet(street);
        setZIP(zip);
        setCity(city);
        setCountry(country);
    }

    public AddressCore(AddressEntity address) {
        super(address);
    }

    @Override
    public Street getStreet() {
        return new Street(getEntity().getStreet());
    }

    @Override
    public Address setStreet(Street street) {
        getEntity().setStreet(SimpleValueObject.nullSafe(street));
        return this;
    }

    @Override
    public ZIP getZIP() {
        return new ZIP(getEntity().getZip());
    }

    @Override
    public Address setZIP(ZIP zip) {
        getEntity().setZip(SimpleValueObject.nullSafe(zip));
        return this;
    }

    @Override
    public City getCity() {
        return new City(getEntity().getCity());
    }

    @Override
    public Address setCity(City city) {
        getEntity().setCity(SimpleValueObject.nullSafe(city));
        return this;
    }

    @Override
    public Country getCountry() {
        return new Country(getEntity().getCountry());
    }

    @Override
    public Address setCountry(Country country) {
        getEntity().setCountry(SimpleValueObject.nullSafe(country));
        return this;
    }
}