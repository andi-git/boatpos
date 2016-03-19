package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractMasterDataEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity of a user.
 */
@Entity
@Table(name = "user")
public class UserEntity extends AbstractMasterDataEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String password;

    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private CompanyEntity company;

    public UserEntity() {
    }

    public UserEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name, String password, CompanyEntity company) {
        super(id, version, enabled, priority, ' ', pictureUrl, pictureUrlThumb);
        this.name = name;
        this.password = password;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }
}
