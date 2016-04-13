package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractMasterDataEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Entity of a tax-set.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "productgroup")
public class ProductGroupEntity extends AbstractMasterDataEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

    @Valid
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private TaxSetEntity taxSet;

    @Valid
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private CashBoxEntity cashBox;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductEntity> products;

    public ProductGroupEntity() {
    }

    public ProductGroupEntity(Long id, Integer version, Boolean enabled, Integer priority, Character keyBinding, String pictureUrl, String pictureUrlThumb, String name, TaxSetEntity taxSet, CashBoxEntity cashBox, Set<ProductEntity> products) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        this.name = name;
        this.taxSet = taxSet;
        this.cashBox = cashBox;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaxSetEntity getTaxSet() {
        return taxSet;
    }

    public void setTaxSet(TaxSetEntity taxSet) {
        this.taxSet = taxSet;
    }

    public CashBoxEntity getCashBox() {
        return cashBox;
    }

    public void setCashBox(CashBoxEntity cashBox) {
        this.cashBox = cashBox;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }
}
