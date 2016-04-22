package org.regkas.service.api.bean;

import org.boatpos.common.service.api.bean.AbstractBean;

/**
 * An ip-address.
 */
public class IpAddressBean extends AbstractBean {

    private String ipAddress;

    public IpAddressBean() {
    }

    public IpAddressBean(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
