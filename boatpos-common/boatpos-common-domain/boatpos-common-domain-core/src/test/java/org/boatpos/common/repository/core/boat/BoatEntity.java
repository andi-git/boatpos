package org.boatpos.common.repository.core.boat;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractMasterDataEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Representation of a boat.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "boat")
public class BoatEntity extends AbstractMasterDataEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

    @NotNull
    @Min(0)
    @Digits(integer = 5, fraction = 2)
    @Expose
    private BigDecimal price;

    public BoatEntity() {
    }

    public BoatEntity(Long id, Integer version, String name, BigDecimal price, Integer priority, boolean enabled, String pictureUrlThumb, String pictureUrl, Character keyBinding) {
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

    public static BoatEntity createDummyWithoutId1() {
        return new BoatEntity(null, 1, "dummy", new BigDecimal("10.5"), 1, true, "pic", "thumb", 'c');
    }

    public static BoatEntity createDummyWithoutId2() {
        return new BoatEntity(null, 1, "dummy2", new BigDecimal("10.5"), 1, true, "pic", "thumb", 'c');
    }

    public static BoatEntity createDummyWithId1() {
        return new BoatEntity(99L, 1, "dummy2", new BigDecimal("10.5"), 1, true, "pic", "thumb", 'c');
    }

    public static BoatEntity createDummyWithId2() {
        return new BoatEntity(98L, 1, "dummy2", new BigDecimal("10.5"), 1, true, "pic", "thumb", 'c');
    }
}
