package org.regkas.domain.core.context;

import org.boatpos.common.util.qualifiers.Current;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.repository.CashBoxRepository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@RequestScoped
public class CashBoxContextCore extends DomainModelContext<CashBox> implements CashBoxContext {

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
