package org.boatpos.repository.api.model;

import org.boatpos.common.repository.api.model.DomainModelWithDto;
import org.boatpos.common.repository.api.values.Priority;
import org.boatpos.model.HolidayEntity;
import org.boatpos.model.PrinterEntity;
import org.boatpos.repository.api.values.Day;
import org.boatpos.repository.api.values.IpAddress;
import org.boatpos.repository.api.values.Name;
import org.boatpos.service.api.bean.HolidayBean;
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
