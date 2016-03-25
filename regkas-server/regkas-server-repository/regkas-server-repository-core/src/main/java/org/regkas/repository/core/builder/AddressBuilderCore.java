package org.regkas.repository.core.builder;


import org.boatpos.common.repository.core.builder.DomainModelBuilderCore;
import org.regkas.model.AddressEntity;
import org.regkas.repository.api.builder.AddressBuilder;
import org.regkas.repository.api.model.Address;
import org.regkas.repository.api.values.City;
import org.regkas.repository.api.values.Country;
import org.regkas.repository.api.values.Street;
import org.regkas.repository.api.values.ZIP;
import org.regkas.repository.core.model.AddressCore;

import javax.enterprise.context.Dependent;

@Dependent
public class AddressBuilderCore
        extends DomainModelBuilderCore<AddressBuilder, Address, AddressCore, AddressEntity>
        implements AddressBuilder {

    private Street street;
    private ZIP zip;
    private City city;
    private Country country;

    @Override
    public Address build() {
        return new AddressCore(id, version, street, zip, city, country);
    }

    @Override
    public AddressBuilder add(Street street) {
        this.street = street;
        return this;
    }

    @Override
    public AddressBuilder add(ZIP zip) {
        this.zip = zip;
        return this;
    }

    @Override
    public AddressBuilder add(City city) {
        this.city = city;
        return this;
    }

    @Override
    public AddressBuilder add(Country country) {
        this.country = country;
        return this;
    }
}
