package org.boatpos.service.api.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompanyBean {

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @Size(min = 3, max = 100)
    private String street;

    @NotNull
    @Size(min = 3, max = 100)
    private String zip;

    @NotNull
    @Size(min = 3, max = 100)
    private String country;

    @Size(min = 3, max = 100)
    private String phone;

    @Size(min = 3, max = 100)
    private String mail;

    @NotNull
    @Size(min = 3, max = 100)
    private String atu;

    public CompanyBean() {
    }

    public CompanyBean(String name, String street, String zip, String country, String phone, String mail, String atu) {
        this.street = street;
        this.name = name;
        this.zip = zip;
        this.country = country;
        this.phone = phone;
        this.mail = mail;
        this.atu = atu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAtu() {
        return atu;
    }

    public void setAtu(String atu) {
        this.atu = atu;
    }
}
