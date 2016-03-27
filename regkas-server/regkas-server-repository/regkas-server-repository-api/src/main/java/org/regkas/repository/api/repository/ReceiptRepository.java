package org.regkas.repository.api.repository;

import org.boatpos.common.repository.api.repository.DomainModelRepository;
import org.regkas.repository.api.builder.ReceiptBuilder;
import org.regkas.repository.api.model.Receipt;
import org.regkas.service.api.bean.Period;

import java.util.List;

/**
 * The repository for the {@link Receipt}.
 */
public interface ReceiptRepository extends DomainModelRepository<Receipt, ReceiptBuilder> {

    /**
     * Get all {@link Receipt}s for a {@link Period}.
     *
     * @param period the {@link Period} to get the {@link Receipt}s for
     * @return all {@link Receipt}s for a certain {@link Period}
     */
    List<Receipt> loadBy(Period period);
}
