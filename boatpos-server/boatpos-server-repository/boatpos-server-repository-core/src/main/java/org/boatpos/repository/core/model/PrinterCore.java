package org.boatpos.repository.core.model;

import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Priority;
import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.common.repository.api.values.Version;
import org.boatpos.common.repository.core.model.DomainModelCore;
import org.boatpos.model.PrinterEntity;
import org.boatpos.repository.api.model.Printer;
import org.boatpos.repository.api.values.IpAddress;
import org.boatpos.repository.core.mapping.PrinterMapping;
import org.boatpos.service.api.bean.PrinterBean;

import static com.google.common.base.Preconditions.checkNotNull;

public class PrinterCore extends DomainModelCore<Printer, PrinterEntity> implements Printer {

    public PrinterCore(DomainId id,
                       Version version,
                       IpAddress ipAddress,
                       Priority priority) {
        super(id, version);
        checkNotNull(ipAddress, "'ipAddress' must not be null");
        checkNotNull(priority, "'priority' must not be null");
        setIpAddress(ipAddress);
        setPriority(priority);
    }

    public PrinterCore(PrinterEntity PrinterEntity) {
        super(PrinterEntity);
    }

    public PrinterCore(PrinterBean PrinterBean) {
        this(PrinterMapping.fromCDI().mapDto(PrinterBean));
    }

    @Override
    public IpAddress getIpAddress() {
        return new IpAddress(getEntity().getIpAddress());
    }

    @Override
    public Printer setIpAddress(IpAddress ipAddress) {
        getEntity().setIpAddress(SimpleValueObject.nullSafe(ipAddress));
        return this;
    }

    @Override
    public Priority getPriority() {
        return new Priority(getEntity().getPriority());
    }

    @Override
    public Printer setPriority(Priority priority) {
        getEntity().setPriority(SimpleValueObject.nullSafe(priority));
        return this;
    }

    @Override
    public PrinterBean asDto() {
        return PrinterMapping.fromCDI().mapEntity(getEntity());
    }
}