package org.boatpos.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Representation of a holiday.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "holiday")
public class HolidayEntity extends AbstractEntity {

    /**
     * The date of the holiday.
     */
    @NotNull
    @Expose
    private LocalDate day;

    /**
     * The name of the holiday.
     */
    @Expose
    private String name;

    public HolidayEntity() {
    }

    public HolidayEntity(Long id, Integer version, LocalDate day, String name) {
        super(id, version);
        this.day = day;
        this.name = name;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String description) {
        this.name = description;
    }
}
