package org.regkas.service.core;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.ReceiptTypeJahr;
import org.regkas.repository.api.model.ReceiptTypeMonat;
import org.regkas.repository.api.model.ReceiptTypeStart;
import org.regkas.repository.api.model.ReceiptTypeTag;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.service.api.JournalService;
import org.regkas.service.api.ReceiptService;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.SaleBean;
import org.regkas.service.core.journal.CashboxJournalEvent;
import org.regkas.service.core.receipt.HandleSignatureDeviceAvailability;
import org.regkas.service.core.receipt.ReceiptCreator;
import org.regkas.service.core.receipt.ReceiptTypeConverter;

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
    private ReceiptService receiptService;

    @Inject
    private JournalService journalService;

    @Override
    public BillBean sale(SaleBean sale) {
        Optional<Receipt> lastReceiptOptional = receiptRepository.loadLastReceipt(cashBox);
        ReceiptType receiptType = receiptTypeConverter.convertToReceiptType(new Name(sale.getReceiptType()));
        if (lastReicptNeededButNotAvailable(receiptType, lastReceiptOptional)) {
            throw new RuntimeException("no last-receipt available");
        } else if (isStartReceiptButThisWasAlreadyCreated(receiptType)) {
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
    private boolean lastReicptNeededButNotAvailable(ReceiptType receiptType, Optional<Receipt> lastReceiptOptional) {
        return receiptType.isLastReceiptMandatory().get() && !lastReceiptOptional.isPresent();
    }

    private boolean isStartReceiptButThisWasAlreadyCreated(ReceiptType receiptType) {
        return receiptType instanceof ReceiptTypeStart && receiptService.isStartReceiptCreated();
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
