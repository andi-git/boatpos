package org.regkas.service.api;

import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.SaleBean;

public interface SaleService {

    BillBean sale(SaleBean sale);

    boolean isSignatureDeviceAvailable();
}
