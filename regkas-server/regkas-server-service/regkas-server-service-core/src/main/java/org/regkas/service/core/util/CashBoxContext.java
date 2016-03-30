package org.regkas.service.core.util;

import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.CashBox;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import java.util.Optional;

/**
 * Context for the {@link CashBox}.
 */
@RequestScoped
public class CashBoxContext {

    private CashBox cashBox;

    public void set(CashBox cashBox) {
        this.cashBox = cashBox;
    }

    public void set(Optional<CashBox> cashBox) {
        if (cashBox.isPresent()) this.cashBox = cashBox.get();
    }

    public void clear() {
        this.cashBox = null;
    }

    @Produces
    @Current
    @RequestScoped
    public CashBox get() {
        return this.cashBox;
    }
}
