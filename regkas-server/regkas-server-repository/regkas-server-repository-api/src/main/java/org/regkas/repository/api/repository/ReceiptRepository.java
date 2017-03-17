package org.regkas.repository.api.repository;

import org.boatpos.common.repository.api.repository.DomainModelRepository;
import org.regkas.repository.api.builder.ReceiptBuilder;
import org.regkas.repository.api.model.*;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.service.api.bean.Period;

import java.util.List;
import java.util.Optional;

/**
 * The repository for the {@link Receipt}.
 */
public interface ReceiptRepository extends DomainModelRepository<Receipt, ReceiptBuilder> {

    /**
     * Get all {@link Receipt}s for a {@link Period}.
     *
     * @param period  the {@link Period} to get the {@link Receipt}s for
     * @param cashBox the {@link CashBox} to get the receipts for
     * @return all {@link Receipt}s for a certain {@link Period}
     */
    List<Receipt> loadBy(Period period, CashBox cashBox);

    /**
     * Get the last {@link Receipt} for a concrete {@link CashBox}.
     *
     * @param cashBox the {@link CashBox} to get the last receipt for
     * @return the last {@link Receipt} for a concrete {@link CashBox}
     */
    Optional<Receipt> loadLastReceipt(CashBox cashBox);

    /**
     * Get the {@link Receipt} by it's {@link ReceiptId}.
     *
     * @param receiptId the {@link ReceiptId} of the {@link Receipt}
     * @param cashBox   the {@link CashBox} to get the last receipt for
     * @return the {@link Receipt}
     */
    Optional<Receipt> loadBy(ReceiptId receiptId, CashBox cashBox);

    /**
     * Load all DEPs that are within the {@link Period} for the {@link CashBox}.
     *
     * @param period  the {@link Period} to load the DEP for
     * @param cashBox the {@link CashBox} to load the DEP for
     * @return a {@link List} of all DEPs as {@link String} (JSON-format)
     */
    List<String> loadDEPFor(Period period, CashBox cashBox);

    /**
     * Load all available {@link Receipt}s without a DEP.
     *
     * @return a {@link List} of all availble {@link Receipt}s without a DEP
     */
    List<Receipt> loadAllWithoutDEP();

    /**
     * Load the latest {@link Receipt} with {@link ReceiptTypeStart}.
     *
     * @param cashBox the current {@link CashBox}
     * @return the latest {@link Receipt} with {@link ReceiptTypeStart}
     */
    Optional<Receipt> loadLatestWithReceiptTypeStart(CashBox cashBox);

    /**
     * Load the latest {@link Receipt} with {@link ReceiptTypeMonat}.
     *
     * @param cashBox the current {@link CashBox}
     * @return the latest {@link Receipt} with {@link ReceiptTypeMonat}
     */
    Optional<Receipt> loadLatestWithReceiptTypeMonat(CashBox cashBox);

    /**
     * Load the latest {@link Receipt} with {@link ReceiptTypeJahr}.
     *
     * @param cashBox the current {@link CashBox}
     * @return the latest {@link Receipt} with {@link ReceiptTypeJahr}
     */
    Optional<Receipt> loadLatestWithReceiptTypeJahr(CashBox cashBox);

    List<String> loadCompactJWSRepresentations(Period period, CashBox cashBox);

    List<String> loadCompactJWSRepresentationsWithSignatureDeviceAvailable(Period period, CashBox cashBox);

    List<String> loadCompactJWSRepresentationsWithSignatureDeviceNotAvailable(Period period, CashBox cashBox);

    Optional<Receipt> loadLastWithSignatureDeviceNotAvailable(CashBox cashBox);
}
