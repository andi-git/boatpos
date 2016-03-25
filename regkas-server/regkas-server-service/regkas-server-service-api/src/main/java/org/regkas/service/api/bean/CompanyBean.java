package org.regkas.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractMasterDataBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompanyBean extends AbstractMasterDataBean {

    @NotNull
    @Size(min = 3, max = 100)
    @Expose
    private String name;

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
    private String city;

    @NotNull
    @Size(min = 3, max = 100)
    @Expose
    private String country;

    @Size(min = 3, max = 100)
    @Expose
    private String phone;

    @Size(min = 3, max = 100)
    @Expose
    private String mail;

    @NotNull
    @Size(min = 3, max = 100)
    @Expose
    private String atu;

    public CompanyBean() {
    }

    public CompanyBean(Long id, Integer version, boolean enabled, Integer priority, Character keyBinding, String pictureUrl, String pictureUrlThumb, String name, String street, String zip, String city, String country, String phone, String mail, String atu) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
