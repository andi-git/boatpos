package org.regkas.domain.core.repository;

import org.boatpos.common.domain.core.JPAHelper;
import org.boatpos.common.domain.core.respository.DomainModelRepositoryCore;
import org.regkas.domain.core.model.ReceiptElementCore;
import org.regkas.model.ReceiptElementEntity;
import org.regkas.domain.api.builder.ReceiptElementBuilder;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.ReceiptElement;
import org.regkas.domain.api.repository.ReceiptElementRepository;
import org.regkas.domain.api.values.ProductGroupIncomeResult;
import org.regkas.domain.core.builder.ReceiptElementBuilderCore;
import org.regkas.service.api.bean.Period;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class ReceiptElementRepositoryCore extends DomainModelRepositoryCore<ReceiptElement, ReceiptElementCore, ReceiptElementEntity, ReceiptElementBuilder, ReceiptElementBuilderCore> implements ReceiptElementRepository {

    @Inject
    private JPAHelper jpaHelper;

    @Override
    public List<ReceiptElement> loadBy(Period period, CashBox cashBox) {
        checkNotNull(period, "'period' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        return loadAll("receiptElement.getBetweenByCashBox",
                ReceiptElementCore::new,
                (query) -> query
                        .setParameter("start", period.getStartDay())
                        .setParameter("end", period.getEndDay())
                        .setParameter("cashBoxId", cashBox.getId().get()));
    }

    @Override
    public List<ProductGroupIncomeResult> incomeByProductGroupFor(Period period, CashBox cashBox) {
        checkNotNull(period, "'period' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        return jpaHelper.createNamedQuery("receiptElement.getSumGroupedByProductGroupForPeriodByCashBox", ProductGroupIncomeResult.class)
                .setParameter("start", period.getStartDay())
                .setParameter("end", period.getEndDay())
                .setParameter("cashBoxId", cashBox.getId().get())
                .getResultList();
    }
}
