package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity for the address.
 */
@Entity
@Table(name = "address")
public class AddressEntity extends AbstractEntity {

    @NotNull
    @Size(min = 3, max = 100)
    @Expose
    private String street;

    @NotNull
    @Size(min = 3, max = 100)
    @Expose
    private String zip;

    @NotNull
    @Size(min = 3, max = 100)
    @Expose
    private String country;

    public AddressEntity() {
    }

    public AddressEntity(String street, String zip, String country) {
        this.street = street;
        this.zip = zip;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
