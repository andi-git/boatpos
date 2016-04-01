package org.regkas.service.core.context;

import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.repository.CashBoxRepository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Optional;

/**
 * Context for the {@link CashBox}.
 */
@RequestScoped
public class CashBoxContext {

    @Inject
    private CashBoxRepository cashBoxRepository;

    private DomainId id;

    public void set(CashBox cashBox) {
        if (cashBox != null) this.id = cashBox.getId();
    }

    public void set(Optional<CashBox> cashBox) {
        if (cashBox.isPresent()) set(cashBox.get());
    }

    public void clear() {
        this.id = null;
    }

    @Produces
    @Current
    @RequestScoped
    public CashBox get() {
        return id == null ? null : cashBoxRepository.loadBy(id).orElse(null);
    }
}
