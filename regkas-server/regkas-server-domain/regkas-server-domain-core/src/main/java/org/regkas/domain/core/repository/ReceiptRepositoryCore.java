package org.regkas.domain.core.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.Dependent;

import org.boatpos.common.domain.core.respository.DomainModelRepositoryCore;
import org.regkas.domain.api.builder.ReceiptBuilder;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.ReceiptId;
import org.regkas.domain.core.builder.ReceiptBuilderCore;
import org.regkas.domain.core.model.ReceiptCore;
import org.regkas.model.ReceiptEntity;
import org.regkas.service.api.bean.Period;

@Dependent
public class ReceiptRepositoryCore extends DomainModelRepositoryCore<Receipt, ReceiptCore, ReceiptEntity, ReceiptBuilder, ReceiptBuilderCore>
        implements
            ReceiptRepository {

    @Override
    public List<Receipt> loadBy(Period period, CashBox cashBox) {
        checkNotNull(period, "'period' must not be null");
        return loadAll(
            "receipt.getBetweenByCashBox",
            ReceiptCore::new,
            (query) -> query.setParameter("start", period.getStartDay()).setParameter("end", period.getEndDay()).setParameter(
                "cashBoxId",
                cashBox.getId().get()));
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
        return loadByParameter(
            "receipt.getByReceiptId",
            (query) -> query.setParameter("cashBoxId", cashBox.getId().get()).setParameter("receiptId", receiptId.get()));
    }

    @Override
    public List<String> loadDEPFor(Period period, CashBox cashBox) {
        checkNotNull(period, "'period' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        return jpaHelper()
            .createNamedQuery("receipt.getDEP", String.class)
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
    public Optional<Receipt> loadLatestWithReceiptTypeSchluss(CashBox cashBox) {
        checkNotNull(cashBox, "'cashBox' must not be null");
        return loadLatestWithReceiptType(new Name("Schluss-Beleg"), cashBox);
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

    @Override
    public List<String> loadCompactJWSRepresentations(Period period, CashBox cashBox) {
        checkNotNull(period, "'period' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        return jpaHelper()
            .createNamedQuery("receipt.getCompactJWSRepresentationsBetweenByCashBox", String.class)
            .setParameter("start", period.getStartDay())
            .setParameter("end", period.getEndDay())
            .setParameter("cashBoxId", cashBox.getId().get())
            .getResultList();
    }

    @Override
    public List<String> loadCompactJWSRepresentationsWithSignatureDeviceAvailable(Period period, CashBox cashBox) {
        checkNotNull(period, "'period' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        return jpaHelper()
            .createNamedQuery("receipt.getCompactJWSRepresentationsWhereSignatureDeviceIsAvailableBetweenByCashBox", String.class)
            .setParameter("start", period.getStartDay())
            .setParameter("end", period.getEndDay())
            .setParameter("cashBoxId", cashBox.getId().get())
            .getResultList();
    }

    @Override
    public List<String> loadCompactJWSRepresentationsWithSignatureDeviceNotAvailable(Period period, CashBox cashBox) {
        checkNotNull(period, "'period' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        return jpaHelper()
            .createNamedQuery("receipt.getCompactJWSRepresentationsWhereSignatureDeviceIsNotAvailableBetweenByCashBox", String.class)
            .setParameter("start", period.getStartDay())
            .setParameter("end", period.getEndDay())
            .setParameter("cashBoxId", cashBox.getId().get())
            .getResultList();
    }

    @Override
    public Optional<Receipt> loadLastWithSignatureDeviceNotAvailable(CashBox cashBox) {
        checkNotNull(cashBox, "'cashBox' must not be null");
        return loadByParameter(
            "receipt.getLastWhereSignatureDeviceIsNotAvailable",
            (query) -> query.setParameter("cashBoxId", cashBox.getId().get()));
    }

    @Override
    public Optional<Receipt> loadLastWithSignatureDeviceAvailableBefore(LocalDateTime timeStamp, CashBox cashBox) {
        checkNotNull(cashBox, "'cashBox' must not be null");
        return loadByParameter(
            "receipt.getLastWhereSignatureDeviceIsAvailableBefore",
            (query) -> query.setParameter("cashBoxId", cashBox.getId().get()).setParameter("timestamp", timeStamp));
    }

    @Override
    public Optional<Receipt> loadFirstWhereSignatureDeviceIsNotAvailableAfter(LocalDateTime timeStamp, CashBox cashBox) {
        checkNotNull(timeStamp, "'timeStamp' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        List<Receipt> receipts = loadAll("receipt.getFirstWhereSignatureDeviceIsNotAvailableAfter", (entity) -> {
            try {
                return getTypeDomainModelCore().getDeclaredConstructor(getTypeEntity()).newInstance(entity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, (query) -> query.setParameter("cashBoxId", cashBox.getId().get()).setParameter("timestamp", timeStamp));
        if (receipts.size() > 0) {
            return Optional.of(receipts.get(0));
        } else {
            return Optional.empty();
        }
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
