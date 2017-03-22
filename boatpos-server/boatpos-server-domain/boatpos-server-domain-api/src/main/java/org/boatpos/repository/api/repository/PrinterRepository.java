package org.boatpos.repository.api.repository;

import org.boatpos.common.repository.api.repository.DomainModelRepository;
import org.boatpos.repository.api.builder.PrinterBuilder;
import org.boatpos.repository.api.model.Printer;
import org.boatpos.repository.api.values.IpAddress;

/**
 * The repository for the {@link Printer}.
 */
public interface PrinterRepository extends DomainModelRepository<Printer, PrinterBuilder> {

    /**
     * Get the one and only {@link Printer}.
     *
     * @return the one and only {@link Printer}
     */
    Printer load();

}
