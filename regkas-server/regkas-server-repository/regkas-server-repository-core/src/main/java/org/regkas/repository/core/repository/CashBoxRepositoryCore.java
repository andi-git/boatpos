package org.regkas.repository.core.repository;

import org.boatpos.common.repository.core.respository.MasterDataRepositoryCore;
import org.regkas.model.CashBoxEntity;
import org.regkas.repository.api.builder.CashBoxBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.builder.CashBoxBuilderCore;
import org.regkas.repository.core.model.CashBoxCore;

import javax.enterprise.context.Dependent;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class CashBoxRepositoryCore extends MasterDataRepositoryCore<CashBox, CashBoxCore, CashBoxEntity, CashBoxBuilder, CashBoxBuilderCore> implements CashBoxRepository {

    @Override
    public Optional<CashBox> loadBy(Name name) {
        checkNotNull(name, "'name' must not be null");
        return loadByParameter("cashbox.getByName", (query) -> query.setParameter("name", name.get()));
    }

    @Override
    protected String namedQueryPrefix() {
        return "cashbox";
    }
}
