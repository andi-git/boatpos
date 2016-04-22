package org.boatpos.repository.api.builder;

import org.boatpos.common.repository.api.builder.DomainModelBuilderWithDto;
import org.boatpos.common.repository.api.values.Priority;
import org.boatpos.model.PrinterEntity;
import org.boatpos.repository.api.model.Printer;
import org.boatpos.repository.api.values.IpAddress;
import org.boatpos.service.api.bean.PrinterBean;

/**
 * Builder for {@link Printer}.
 */
public interface PrinterBuilder extends DomainModelBuilderWithDto<PrinterBuilder, Printer, PrinterEntity, PrinterBean> {

    PrinterBuilder add(IpAddress ipAddress);

    PrinterBuilder add(Priority priority);
}
