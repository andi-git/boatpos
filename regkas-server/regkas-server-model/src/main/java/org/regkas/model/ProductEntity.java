package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.model.AbstractMasterDataEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Representation of a product.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "product")
public class ProductEntity extends AbstractMasterDataEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

    @Valid
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private ProductGroupEntity productGroup;

    @Valid
    @Min(0)
    @Expose
    private BigDecimal price;

    @NotNull
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Expose
    private Set<ReceiptElementEntity> receiptElements;

    public ProductEntity() {
    }

    public ProductEntity(Long id, Integer version, Boolean enabled, int priority, char keyBinding, String pictureUrlThumb, String pictureUrl, String name, ProductGroupEntity productGroup, Set<ReceiptElementEntity> receiptElements, BigDecimal price) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        this.name = name;
        this.productGroup = productGroup;
        this.receiptElements = receiptElements;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductGroupEntity getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupEntity productGroup) {
        this.productGroup = productGroup;
    }

    public Set<ReceiptElementEntity> getReceiptElements() {
        return receiptElements;
    }

    public void setReceiptElements(Set<ReceiptElementEntity> receiptElements) {
        this.receiptElements = receiptElements;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
