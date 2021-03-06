package org.regkas.domain.core.repository;

import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.core.respository.MasterDataRepositoryCore;
import org.regkas.domain.core.builder.ReceiptTypeBuilderHolder;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.domain.api.builder.ReceiptTypeBuilder;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.repository.ReceiptTypeRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.core.builder.ReceiptTypeBuilderCore;
import org.regkas.domain.core.model.ReceiptTypeCore;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class ReceiptTypeRepositoryCore extends MasterDataRepositoryCore<ReceiptType, ReceiptTypeCore, ReceiptTypeEntity, ReceiptTypeBuilder, ReceiptTypeBuilderCore> implements ReceiptTypeRepository {

    @Inject
    private ReceiptTypeBuilderHolder receiptTypeBuilderHolder;

    @Override
    public Optional<ReceiptType> loadBy(Name name) {
        checkNotNull(name, "'name' must not be null");
        return receiptTypeBuilderHolder.getReceiptTypeFor(
                jpaHelper().getSingleResult(
                        jpaHelper()
                                .createNamedQuery("receipttype.getByName", ReceiptTypeEntity.class)
                                .setParameter("name", name.get())
                                .getResultList())
                        .orElseThrow(() -> new RuntimeException("unable to get receipt-type '" + name.get() + "' from database")));
    }

    @Override
    public List<ReceiptType> loadAll() {
        return loadAll(namedQueryPrefix() + ".getAll");
    }

    @Override
    public List<ReceiptType> loadAll(Enabled enabled) {
        checkNotNull(enabled, "'enabled' must not be null");
        return loadAll(namedQueryPrefix() + ".getAll" + (enabled.get() ? "Enabled" : "Disabled"));
    }

    private List<ReceiptType> loadAll(String namedQuery) {
        return loadAll(namedQuery, (entity) -> {
            try {
                Optional<ReceiptType> receiptType = receiptTypeBuilderHolder.getReceiptTypeFor(entity);
                if (receiptType.isPresent()) {
                    return receiptType.get();
                } else {
                    throw new RuntimeException("no receipt-type-builder for " + entity.getClass().getName() + " avialable");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    protected String namedQueryPrefix() {
        return "receipttype";
    }
}
