package org.regkas.service.api;

import org.regkas.service.api.bean.IpAddressBean;

/**
 * Service for printer.
 */
public interface PrinterService {

    /**
     * Get the ip-address of the printer.
     *
     * @return the ip-address of the printer
     */
    IpAddressBean loadIp();

    /**
     * Save the new ip-address.
     *
     * @param ipAddressBean new ip-address
     */
    void saveIp(IpAddressBean ipAddressBean);
}
