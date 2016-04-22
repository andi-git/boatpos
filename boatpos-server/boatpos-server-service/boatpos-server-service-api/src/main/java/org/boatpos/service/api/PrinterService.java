package org.boatpos.service.api;

import org.boatpos.service.api.bean.IpAddressBean;
import org.boatpos.service.api.bean.PrinterBean;

/**
 * Service for {@link PrinterBean}s.
 */
public interface PrinterService {

    /**
     * Get the one and only {@link PrinterBean}.
     *
     * @return the one and only {@link PrinterBean}
     */
    PrinterBean load();

    /**
     * Save the new ip-address.
     *
     * @param ipAddressBean new ip-address
     * @return updated printer
     */
    PrinterBean save(IpAddressBean ipAddressBean);
}
