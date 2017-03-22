package org.boatpos.domain.core.builder;

import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.domain.core.builder.DomainModelBuilderWithDtoCore;
import org.boatpos.domain.core.model.PrinterCore;
import org.boatpos.model.PrinterEntity;
import org.boatpos.domain.api.builder.PrinterBuilder;
import org.boatpos.domain.api.model.Printer;
import org.boatpos.domain.api.values.IpAddress;
import org.boatpos.service.api.bean.PrinterBean;

import javax.enterprise.context.Dependent;

@Dependent
public class PrinterBuilderCore extends DomainModelBuilderWithDtoCore<PrinterBuilder, Printer, PrinterCore, PrinterEntity, PrinterBean> implements PrinterBuilder {

    protected IpAddress ipAddress;

    protected Priority priority;

    @Override
    public PrinterBuilder add(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    @Override
    public PrinterBuilder add(Priority priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public Printer build() {
        return new PrinterCore(id, version, ipAddress, priority);
    }
}
