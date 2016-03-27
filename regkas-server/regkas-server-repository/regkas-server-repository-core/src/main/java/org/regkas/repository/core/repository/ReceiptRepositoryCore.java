package org.regkas.repository.core.repository;

import org.boatpos.common.repository.core.respository.DomainModelRepositoryCore;
import org.regkas.model.ReceiptEntity;
import org.regkas.repository.api.builder.ReceiptBuilder;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.core.builder.ReceiptBuilderCore;
import org.regkas.repository.core.model.ReceiptCore;
import org.regkas.service.api.bean.Period;

import javax.enterprise.context.Dependent;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class ReceiptRepositoryCore extends DomainModelRepositoryCore<Receipt, ReceiptCore, ReceiptEntity, ReceiptBuilder, ReceiptBuilderCore> implements ReceiptRepository {

    @Override
    public List<Receipt> loadBy(Period period) {
        checkNotNull(period, "'period' must not be null");
        return loadAll("receipt.getBetween",
                ReceiptCore::new,
                (query) -> query
                        .setParameter("start", period.getStart())
                        .setParameter("end", period.getEnd()));
    }
}
