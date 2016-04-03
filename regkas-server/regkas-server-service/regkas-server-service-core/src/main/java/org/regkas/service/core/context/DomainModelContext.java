package org.regkas.service.core.context;

import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.values.DomainId;

import java.util.Optional;

/**
 * Abstract class for all contexts based on a {@link DomainModel}.
 */
abstract class DomainModelContext<MODEL extends DomainModel> {

    private Optional<DomainId> id = Optional.empty();

    public void set(MODEL domainModel) {
        if (domainModel != null) this.id = Optional.of(domainModel.getId());
    }

    public void set(Optional<MODEL> domainModel) {
        if (domainModel.isPresent()) set(domainModel.get());
    }

    public void clear() {
        this.id = Optional.empty();
    }

    protected Optional<DomainId> getId() {
        return this.id;
    }

    public abstract MODEL get();
}
