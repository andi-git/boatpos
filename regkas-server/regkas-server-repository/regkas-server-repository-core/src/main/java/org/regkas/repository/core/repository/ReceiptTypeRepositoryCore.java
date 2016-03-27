package org.regkas.repository.core.repository;

import org.boatpos.common.repository.core.respository.MasterDataRepositoryCore;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.repository.api.builder.ReceiptTypeBuilder;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.repository.ReceiptTypeRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.builder.ReceiptTypeBuilderCore;
import org.regkas.repository.core.model.ReceiptTypeCore;

import javax.enterprise.context.Dependent;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class ReceiptTypeRepositoryCore extends MasterDataRepositoryCore<ReceiptType, ReceiptTypeCore, ReceiptTypeEntity, ReceiptTypeBuilder, ReceiptTypeBuilderCore> implements ReceiptTypeRepository {

    @Override
    public Optional<ReceiptType> loadBy(Name name) {
        checkNotNull(name, "'name' must not be null");
        return loadByParameter("receipttype.getByName", (query) -> query.setParameter("name", name.get()));
    }

    @Override
    protected String namedQueryPrefix() {
        return "receipttype";
    }
}
