package org.regkas.domain.core.builder;


import org.boatpos.common.domain.core.builder.DomainModelBuilderCore;
import org.regkas.model.AddressEntity;
import org.regkas.domain.api.builder.AddressBuilder;
import org.regkas.domain.api.model.Address;
import org.regkas.domain.api.values.City;
import org.regkas.domain.api.values.Country;
import org.regkas.domain.api.values.Street;
import org.regkas.domain.api.values.ZIP;
import org.regkas.domain.core.model.AddressCore;

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
