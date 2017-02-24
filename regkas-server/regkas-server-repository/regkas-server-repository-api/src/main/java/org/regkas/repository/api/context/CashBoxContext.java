package org.regkas.repository.api.context;

import org.regkas.repository.api.model.CashBox;

import java.util.Optional;

/**
 * Context for the {@link CashBox}.
 */
public interface CashBoxContext {

    CashBox get();

    void set(CashBox cashBox);

    void set(Optional<CashBox> cashBox);

    void clear();
}
