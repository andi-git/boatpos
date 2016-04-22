package org.boatpos.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;
import org.boatpos.common.service.api.bean.LocalDateAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Representation of a printer.
 */
@SuppressWarnings("unused")
public class PrinterBean extends AbstractBeanBasedOnEntity {

    /**
     * The ip-address of the printer.
     */
    @NotNull
    @Size(min = 7, max = 15)
    @Expose
    private String ipAddress;

    public PrinterBean() {

    }

    public PrinterBean(Long id, Integer version, String ipAddress) {
        super(id, version);
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
