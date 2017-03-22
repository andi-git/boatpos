package org.boatpos.common.domain.core.boat;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractMasterDataBean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Representation of a b boat.
 */
@SuppressWarnings("unused")
public class BoatBean extends AbstractMasterDataBean {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

    @NotNull
    @Min(0)
    @Expose
    private BigDecimal price;

    public BoatBean() {
    }

    public BoatBean(Long id, Integer version, String name, BigDecimal price, Integer priority, boolean enabled, Character keyBinding, String pictureUrl, String pictureUrlThumb) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        this.name = name;
        this.price = price;
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

    public static BoatBean createDummyWithoutId1() {
        return new BoatBean(null, 1, "dummy", new BigDecimal("10.5"), 1, true, 'a', "pic", "thumb");
    }

    public static BoatBean createDummyWithoutId2() {
        return new BoatBean(null, 1, "dummy2", new BigDecimal("10.5"), 1, true, 'a', "pic", "thumb");
    }

    public static BoatBean createDummyWithIdExisting() {
        return new BoatBean(1L, 1, "dummy", new BigDecimal("10.5"), 1, true, 'a', "pic", "thumb");
    }

    public static BoatBean createDummyWithIdNotExisting1() {
        return new BoatBean(99999L, 1, "dummy", new BigDecimal("10.5"), 1, true, 'a', "pic", "thumb");
    }

    public static BoatBean createDummyWithIdNotExisting2() {
        return new BoatBean(99998L, 1, "dummy", new BigDecimal("10.5"), 1, true, 'a', "pic", "thumb");
    }
}
