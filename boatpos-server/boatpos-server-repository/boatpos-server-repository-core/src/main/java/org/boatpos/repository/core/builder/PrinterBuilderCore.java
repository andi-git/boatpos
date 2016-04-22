package org.boatpos.repository.core.builder;

import org.boatpos.common.repository.api.values.Priority;
import org.boatpos.common.repository.core.builder.DomainModelBuilderWithDtoCore;
import org.boatpos.model.PrinterEntity;
import org.boatpos.repository.api.builder.PrinterBuilder;
import org.boatpos.repository.api.model.Printer;
import org.boatpos.repository.api.values.IpAddress;
import org.boatpos.repository.core.model.PrinterCore;
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
