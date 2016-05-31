package org.regkas.repository.api.repository;

import org.boatpos.common.repository.api.repository.DomainModelRepository;
import org.regkas.repository.api.builder.ReceiptElementBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.values.ProductGroupIncomeResult;
import org.regkas.service.api.bean.Period;

import java.util.List;

/**
 * The repository for the {@link ReceiptElement}.
 */
public interface ReceiptElementRepository extends DomainModelRepository<ReceiptElement, ReceiptElementBuilder> {

    /**
     * Get all {@link ReceiptElement}s for a {@link Period}.
     *
     * @param period  the {@link Period} to get the {@link Receipt}s for
     * @param cashBox the {@link CashBox} to get the receipts for
     * @return all {@link ReceiptElement}s for a certain {@link Period}
     */
    List<ReceiptElement> loadBy(Period period, CashBox cashBox);

    /**
     * Get the income for all {@link ProductGroup}s of a certain {@link Period} for a {@link CashBox}.
     *
     * @param period  the {@link Period} to get the {@link Receipt}s for
     * @param cashBox the {@link CashBox} to get the receipts for
     * @return the income for all {@link ProductGroup}s of a certain {@link Period} for a {@link CashBox}
     */
    List<ProductGroupIncomeResult> incomeByProductGroupFor(Period period, CashBox cashBox);
}
