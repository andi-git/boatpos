package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractMasterDataEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Set;

/**
 * Representation of a company.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "company")
public class CompanyEntity extends AbstractMasterDataEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

    @Valid
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private AddressEntity address;

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

    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CashBoxEntity> cashBoxes;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserEntity> users;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductGroupEntity> productGroups;

    public CompanyEntity() {
    }

    public CompanyEntity(Long id, Integer version, Boolean enabled, int priority, String pictureUrlThumb, String pictureUrl, String name, AddressEntity address, String phone, String mail, String atu, Set<CashBoxEntity> cashBoxes, Set<UserEntity> users, Set<ProductGroupEntity> productGroups) {
        super(id, version, enabled, priority, '#', pictureUrl, pictureUrlThumb);
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.atu = atu;
        this.cashBoxes = cashBoxes;
        this.users = users;
        this.productGroups = productGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
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

    public Set<CashBoxEntity> getCashBoxes() {
        return cashBoxes;
    }

    public void setCashBoxes(Set<CashBoxEntity> cashBoxes) {
        this.cashBoxes = cashBoxes;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public Set<ProductGroupEntity> getProductGroups() {
        return productGroups;
    }

    public void setProductGroups(Set<ProductGroupEntity> productGroups) {
        this.productGroups = productGroups;
    }
}
