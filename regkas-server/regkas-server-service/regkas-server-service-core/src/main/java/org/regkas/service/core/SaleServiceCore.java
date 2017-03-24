package org.regkas.service.core;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.boatpos.common.util.qualifiers.Current;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.ReceiptTypeJahr;
import org.regkas.domain.api.model.ReceiptTypeMonat;
import org.regkas.domain.api.model.ReceiptTypeTag;
import org.regkas.domain.api.receipt.precondition.StartReceiptAvailable;
import org.regkas.domain.api.receipt.precondition.StartReceiptNotAvailable;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.service.api.JournalService;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.SaleBean;
import org.regkas.service.core.journal.CashboxJournalEvent;
import org.regkas.service.core.receipt.HandleSignatureDeviceAvailability;
import org.regkas.service.core.receipt.ReceiptCreator;
import org.regkas.service.core.receipt.ReceiptTypeConverter;
import org.regkas.service.core.receipt.precondition.PreconditionChecker;

@RequestScoped
public class SaleServiceCore implements SaleService {

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    @Current
    private CashBox cashBox;

    @Inject
    private ReceiptCreator receiptCreator;

    @Inject
    @Any
    private Instance<HandleSignatureDeviceAvailability> handleSignatureDeviceAvailabilities;

    @Inject
    private Event<CashboxJournalEvent> cashboxJournalEvent;

    @Inject
    private ReceiptTypeConverter receiptTypeConverter;

    @Inject
    private JournalService journalService;

    @Inject
    private PreconditionChecker preconditionChecker;

    @Override
    public BillBean sale(SaleBean sale) {
        Optional<Receipt> lastReceiptOptional = receiptRepository.loadLastReceipt(cashBox);
        ReceiptType receiptType = receiptTypeConverter.convertToReceiptType(new Name(sale.getReceiptType()));
        if ( !preconditionChecker.isFulfilled(receiptType, cashBox, StartReceiptAvailable.class)) {
            throw new RuntimeException("no start-receipt available");
        } else if ( !preconditionChecker.isFulfilled(receiptType, cashBox, StartReceiptNotAvailable.class)) {
            throw new RuntimeException("start-receipt was already created");
        } else {
            Receipt receipt = receiptCreator.createReceipt(sale);
            cashboxJournalEvent.fire(
                new CashboxJournalEvent(
                    "create receipt " + receipt.getReceiptId().get() + ", " + receipt.getReceiptType().getName().get(),
                    cashBox,
                    receipt.getReceiptDate().get()));
            BillBean billBean = receipt.asBillBean();
            billBean = addJournal(billBean, receiptType);
            billBean = handleDeviceAvailability(lastReceiptOptional, receipt, billBean);
            return billBean;
        }
    }

    @Override
    public boolean isSignatureDeviceAvailable() {
        boolean signatureDeviceAvailable = true;
        Optional<Receipt> receipt = receiptRepository.loadLastReceipt(cashBox);
        if (receipt.isPresent()) {
            signatureDeviceAvailable = receipt.get().getSignatureDeviceAvailable().get();
        }
        return signatureDeviceAvailable;
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private BillBean handleDeviceAvailability(Optional<Receipt> lastReceiptOptional, Receipt receipt, BillBean billBean) {
        if ("false".equalsIgnoreCase(System.getProperty("boatpos.ignore.handling.of.signature.device.availability", "false"))) {
            Optional<HandleSignatureDeviceAvailability> handleSignatureDeviceAvailability = getHandleSignatureDeviceAvailability(
                receipt,
                lastReceiptOptional);
            if (handleSignatureDeviceAvailability.isPresent()) {
                billBean = handleSignatureDeviceAvailability.get().handle(billBean);
            }
        }
        return billBean;
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<HandleSignatureDeviceAvailability> getHandleSignatureDeviceAvailability(Receipt currentReceipt, Optional<Receipt> lastReceipt) {
        if (lastReceipt.isPresent()) {
            for (HandleSignatureDeviceAvailability handleSignatureDeviceAvailability : handleSignatureDeviceAvailabilities) {
                if (handleSignatureDeviceAvailability.canHandle(currentReceipt, lastReceipt.get())) {
                    return Optional.of(handleSignatureDeviceAvailability);
                }
            }
        }
        return Optional.empty();
    }

    private BillBean addJournal(BillBean billBean, ReceiptType receiptType) {
        if (receiptType instanceof ReceiptTypeTag) {
            billBean.setIncomeBean(
                journalService.totalIncomeFor(
                    billBean.getReceiptDateAndTime().getYear(),
                    billBean.getReceiptDateAndTime().getMonthValue(),
                    billBean.getReceiptDateAndTime().getDayOfMonth()));
        } else if (receiptType instanceof ReceiptTypeMonat) {
            billBean.setIncomeBean(
                journalService.totalIncomeFor(billBean.getReceiptDateAndTime().getYear(), billBean.getReceiptDateAndTime().getMonthValue()));
        } else if (receiptType instanceof ReceiptTypeJahr) {
            billBean.setIncomeBean(journalService.totalIncomeFor(billBean.getReceiptDateAndTime().getYear()));
        }
        return billBean;
    }
}
