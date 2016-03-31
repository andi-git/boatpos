package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractMasterDataEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Entity of a tax-set.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "taxset")
public class TaxSetEntity extends AbstractMasterDataEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

    @NotNull
    @Min(0)
    @Max(100)
    @Expose
    private Integer taxPercent;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Expose
    private Set<ProductGroupEntity> productGroups;

    public TaxSetEntity() {
    }

    public TaxSetEntity(Long id, Integer version, Boolean enabled, Integer priority, String pictureUrl, String pictureUrlThumb, String name, Integer taxPercent, Set<ProductGroupEntity> productGroups) {
        super(id, version, enabled, priority, '#', pictureUrl, pictureUrlThumb);
        this.name = name;
        this.taxPercent = taxPercent;
        this.productGroups = productGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Integer taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Set<ProductGroupEntity> getProductGroups() {
        return productGroups;
    }

    public void setProductGroups(Set<ProductGroupEntity> productGroups) {
        this.productGroups = productGroups;
    }
}
