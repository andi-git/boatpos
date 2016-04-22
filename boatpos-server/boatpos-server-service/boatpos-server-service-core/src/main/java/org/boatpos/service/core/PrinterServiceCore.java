package org.boatpos.service.core;

import org.boatpos.common.service.core.ModelDtoConverter;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.boatpos.repository.api.repository.PrinterRepository;
import org.boatpos.repository.api.values.IpAddress;
import org.boatpos.service.api.PrinterService;
import org.boatpos.service.api.bean.IpAddressBean;
import org.boatpos.service.api.bean.PrinterBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class PrinterServiceCore implements PrinterService {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private PrinterRepository printerRepository;

    @Inject
    private ModelDtoConverter modelDtoConverter;


    @Override
    public PrinterBean load() {
        return printerRepository.load().asDto();
    }

    @Override
    public PrinterBean save(IpAddressBean ipAddressBean) {
        return printerRepository.load().setIpAddress(new IpAddress(ipAddressBean.getIpAddress())).persist().asDto();
    }
}
