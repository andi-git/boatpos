package org.regkas.service.core;

import org.boatpos.common.util.qualifiers.Current;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.values.IpAddress;
import org.regkas.service.api.PrinterService;
import org.regkas.service.api.bean.IpAddressBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class PrinterServiceCore implements PrinterService {

    @Inject
    @Current
    private CashBox cashBox;

    @Override
    public IpAddressBean loadIp() {
        return new IpAddressBean(cashBox.getPrinterIpAddress().get());
    }

    @Override
    public void saveIp(IpAddressBean ipAddressBean) {
        cashBox.setPrinterIpAddress(new IpAddress(ipAddressBean.getIpAddress())).persist();
    }
}
