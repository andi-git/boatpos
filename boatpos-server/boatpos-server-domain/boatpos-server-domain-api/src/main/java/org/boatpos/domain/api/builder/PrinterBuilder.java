package org.boatpos.domain.api.builder;

import org.boatpos.common.domain.api.builder.DomainModelBuilderWithDto;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.domain.api.values.IpAddress;
import org.boatpos.model.PrinterEntity;
import org.boatpos.domain.api.model.Printer;
import org.boatpos.service.api.bean.PrinterBean;

/**
 * Builder for {@link Printer}.
 */
public interface PrinterBuilder extends DomainModelBuilderWithDto<PrinterBuilder, Printer, PrinterEntity, PrinterBean> {

    PrinterBuilder add(IpAddress ipAddress);

    PrinterBuilder add(Priority priority);
}
