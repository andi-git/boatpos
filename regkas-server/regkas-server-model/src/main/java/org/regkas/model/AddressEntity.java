package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

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

    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Expose
    private Set<CompanyEntity> companies;

    public AddressEntity() {
    }

    public AddressEntity(Long id, Integer version, String street, String zip, String country, Set<CompanyEntity> companies) {
        super(id, version);
        this.street = street;
        this.zip = zip;
        this.country = country;
        this.companies = companies;
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

    public Set<CompanyEntity> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<CompanyEntity> companies) {
        this.companies = companies;
    }
}
