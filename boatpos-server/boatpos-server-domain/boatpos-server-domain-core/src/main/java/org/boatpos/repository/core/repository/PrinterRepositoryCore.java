package org.boatpos.repository.core.repository;

import org.boatpos.common.repository.core.respository.DomainModelRepositoryCore;
import org.boatpos.model.PrinterEntity;
import org.boatpos.repository.api.builder.PrinterBuilder;
import org.boatpos.repository.api.model.Printer;
import org.boatpos.repository.api.repository.PrinterRepository;
import org.boatpos.repository.api.values.IpAddress;
import org.boatpos.repository.core.builder.PrinterBuilderCore;
import org.boatpos.repository.core.model.PrinterCore;

import javax.enterprise.context.Dependent;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class PrinterRepositoryCore extends DomainModelRepositoryCore<Printer, PrinterCore, PrinterEntity, PrinterBuilder, PrinterBuilderCore> implements PrinterRepository {

    @Override
    public Printer load() {
        return super.loadAll("printer.get", PrinterCore::new).get(0);
    }
}