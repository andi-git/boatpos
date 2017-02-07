package org.regkas.repository.core.repository;

import org.boatpos.common.repository.core.respository.DomainModelRepositoryCore;
import org.regkas.model.ReceiptEntity;
import org.regkas.repository.api.builder.ReceiptBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.repository.core.builder.ReceiptBuilderCore;
import org.regkas.repository.core.model.ReceiptCore;
import org.regkas.service.api.bean.Period;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class ReceiptRepositoryCore extends DomainModelRepositoryCore<Receipt, ReceiptCore, ReceiptEntity, ReceiptBuilder, ReceiptBuilderCore> implements ReceiptRepository {

    @Override
    public List<Receipt> loadBy(Period period, CashBox cashBox) {
        checkNotNull(period, "'period' must not be null");
        return loadAll("receipt.getBetweenByCashBox",
                ReceiptCore::new,
                (query) -> query
                        .setParameter("start", period.getStartDay())
                        .setParameter("end", period.getEndDay())
                        .setParameter("cashBoxId", cashBox.getId().get()));
    }

    @Override
    public Optional<Receipt> loadLastReceipt(CashBox cashBox) {
        checkNotNull(cashBox, "'cashBox' must not be null");
        return loadByParameter("receipt.getLastByCashBox", (query) -> query.setParameter("cashBoxId", cashBox.getId().get()));
    }

    @Override
    public Optional<Receipt> loadBy(ReceiptId receiptId, CashBox cashBox) {
        checkNotNull(cashBox, "'cashBox' must not be null");
        checkNotNull(receiptId, "'receiptId' must not be null");
        return loadByParameter("receipt.getByReceiptId",
                (query) -> query
                        .setParameter("cashBoxId", cashBox.getId().get())
                        .setParameter("receiptId", receiptId.get()));
    }

    @Override
    public List<String> loadDEPFor(Period period, CashBox cashBox) {
        checkNotNull(period, "'period' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        return jpaHelper().createNamedQuery("receipt.getDEP", String.class)
                .setParameter("start", period.getStartDay())
                .setParameter("end", period.getEndDay())
                .setParameter("cashBoxId", cashBox.getId().get())
                .getResultList();
    }

    @Override
    public List<Receipt> loadAllWithoutDEP() {
        return super.loadAll("receipt.getAllWithoutDEP", ReceiptCore::new);
    }

    @Override
    public Optional<Receipt> loadLatestWithReceiptTypeStart(CashBox cashBox) {
        checkNotNull(cashBox, "'cashBox' must not be null");
        return loadLatestWithReceiptType(new Name("Start-Beleg"), cashBox);
    }

    @Override
    public Optional<Receipt> loadLatestWithReceiptTypeMonat(CashBox cashBox) {
        checkNotNull(cashBox, "'cashBox' must not be null");
        return loadLatestWithReceiptType(new Name("Monats-Beleg"), cashBox);
    }

    @Override
    public Optional<Receipt> loadLatestWithReceiptTypeJahr(CashBox cashBox) {
        checkNotNull(cashBox, "'cashBox' must not be null");
        return loadLatestWithReceiptType(new Name("Jahres-Beleg"), cashBox);
    }

    private Optional<Receipt> loadLatestWithReceiptType(Name receiptTypeName, CashBox cashBox) {
        checkNotNull(cashBox, "'receiptTypeName' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        Optional<Receipt> receipt = Optional.empty();
        List<ReceiptEntity> receiptList = jpaHelper()
                .createNamedQuery("receipt.getAllWithReceiptTypeSortedDescending", ReceiptEntity.class)
                .setParameter("receiptTypeName", receiptTypeName.get())
                .setParameter("cashBoxId", cashBox.getId().get())
                .getResultList();
        if (receiptList.size() > 0) {
            receipt = Optional.of(new ReceiptCore(receiptList.get(0)));
        }
        return receipt;
    }
}
