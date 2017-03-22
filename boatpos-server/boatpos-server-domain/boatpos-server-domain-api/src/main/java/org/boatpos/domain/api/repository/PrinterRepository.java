package org.boatpos.domain.api.repository;

import org.boatpos.common.domain.api.repository.DomainModelRepository;
import org.boatpos.domain.api.builder.PrinterBuilder;
import org.boatpos.domain.api.model.Printer;

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
