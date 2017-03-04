package org.regkas.service.api;

import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.SaleBean;

/**
 * Service for sales.
 */
public interface SaleService {

    /**
     * Perform a sale.
     *
     * @param sale the elements of the sale
     * @return the bill of the sale
     */
    BillBean sale(SaleBean sale);
}
