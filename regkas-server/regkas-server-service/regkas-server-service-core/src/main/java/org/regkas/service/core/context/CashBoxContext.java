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
public class CashBoxContext extends DomainModelContext<CashBox> {

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Override
    @Produces
    @Current
    @RequestScoped
    public CashBox get() {
        return getId().isPresent() ? cashBoxRepository.loadBy(getId().get()).orElse(null) : null;
    }
}
