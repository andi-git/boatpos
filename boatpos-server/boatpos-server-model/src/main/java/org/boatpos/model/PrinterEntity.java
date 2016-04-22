package org.boatpos.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Representation of a holiday.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "printer")
public class PrinterEntity extends AbstractEntity {

    /**
     * The ip-address.
     */
    @NotNull
    @Size(min = 7, max = 15)
    @Expose
    private String ipAddress;

    @Expose
    @NotNull
    @Min(0)
    private Integer priority;

    public PrinterEntity() {
    }

    public PrinterEntity(Long id, Integer version, String ipAddress, Integer priority) {
        super(id, version);
        this.ipAddress = ipAddress;
        this.priority = priority;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
