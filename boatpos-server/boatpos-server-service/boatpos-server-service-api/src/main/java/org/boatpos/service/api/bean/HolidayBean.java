package org.boatpos.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;
import org.boatpos.common.service.api.bean.LocalDateAdapter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Representation of a boat-rental.
 */
@SuppressWarnings("unused")
public class HolidayBean extends AbstractBeanBasedOnEntity {

    /**
     * The date of the {@link HolidayBean}.
     */
    @NotNull
    @Expose
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate day;

    /**
     * The name of the holiday.
     */
    @Expose
    private String name;

    public HolidayBean() {

    }

    public HolidayBean(Long id, Integer version, LocalDate day, String name) {
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

    public void setName(String name) {
        this.name = name;
    }
}
