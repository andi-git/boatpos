package org.boatpos.domain.core.repository;

import org.boatpos.common.domain.core.respository.DomainModelRepositoryCore;
import org.boatpos.domain.core.builder.PrinterBuilderCore;
import org.boatpos.domain.core.model.PrinterCore;
import org.boatpos.model.PrinterEntity;
import org.boatpos.domain.api.builder.PrinterBuilder;
import org.boatpos.domain.api.model.Printer;
import org.boatpos.domain.api.repository.PrinterRepository;

import javax.enterprise.context.Dependent;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class PrinterRepositoryCore extends DomainModelRepositoryCore<Printer, PrinterCore, PrinterEntity, PrinterBuilder, PrinterBuilderCore> implements PrinterRepository {

    @Override
    public Printer load() {
        return super.loadAll("printer.get", PrinterCore::new).get(0);
    }
}