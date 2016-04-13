package org.regkas.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractMasterDataBean;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Representation of a product.
 */
public class ProductBean extends AbstractMasterDataBean {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

    @NotNull
    @Min(0)
    @Expose
    private BigDecimal price;

    @NotNull
    @Expose
    private boolean generic;

    @NotNull
    @Valid
    @Expose
    private ProductGroupBean productGroup;

    public ProductBean() {
    }

    public ProductBean(Long id, Integer version, boolean enabled, Integer priority, Character keyBinding, String pictureUrl, String pictureUrlThumb, String name, BigDecimal price, boolean generic, ProductGroupBean productGroup) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        this.name = name;
        this.price = price;
        this.generic = generic;
        this.productGroup = productGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isGeneric() {
        return generic;
    }

    public void setGeneric(boolean generic) {
        this.generic = generic;
    }

    public ProductGroupBean getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupBean productGroup) {
        this.productGroup = productGroup;
    }
}
