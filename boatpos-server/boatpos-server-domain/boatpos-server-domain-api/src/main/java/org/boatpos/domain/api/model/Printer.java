package org.boatpos.domain.api.model;

import org.boatpos.common.domain.api.model.DomainModelWithDto;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.domain.api.values.IpAddress;
import org.boatpos.model.PrinterEntity;
import org.boatpos.service.api.bean.PrinterBean;

/**
 * The domain model for a holiday.
 */
public interface Printer extends DomainModelWithDto<Printer, PrinterEntity, PrinterBean> {

    IpAddress getIpAddress();

    Printer setIpAddress(IpAddress ipAddress);

    Priority getPriority();

    Printer setPriority(Priority priority);
}
