package org.regkas.service.core.receipt;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Receipt;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.core.email.BillBeanToMailContentConverter;
import org.regkas.service.core.email.SendMailEvent;
import org.regkas.service.core.financialoffice.SignatureDeviceAvailableAgainEvent;
import org.regkas.service.core.journal.CashboxJournalEvent;

@ApplicationScoped
public class SignatureDeviceIsAvailableAgain implements HandleSignatureDeviceAvailability {

    @Inject
    private NullReceiptCreator nullReceiptCreator;

    @Inject
    private ReceiptCreator receiptCreator;

    @Inject
    private BillBeanToMailContentConverter billBeanToMailContentConverter;

    @Inject
    private Event<SendMailEvent> sendMailEvent;

    @Inject
    private Event<SignatureDeviceAvailableAgainEvent> signatureDeviceAvailableAgainEvent;

    @Inject
    private Event<CashboxJournalEvent> cashboxJournalEvent;

    @Inject
    @Current
    private CashBox cashBox;

    @Override
    public boolean canHandle(Receipt currentReceipt, Receipt lastReceipt) {
        return SimpleValueObject.nullSafe(currentReceipt.getSignatureDeviceAvailable()) &&
            !SimpleValueObject.nullSafe(lastReceipt.getSignatureDeviceAvailable());
    }

    @Override
    public BillBean handle(BillBean billBean) {
        checkNotNull(billBean, "'billBean' must not be null");
        notifyCompany(billBean);
        notifyFinancialOffice();
        notifyJournal();
        Receipt sammelReceipt = createNullReceipt();
        billBean.setSammelBeleg(sammelReceipt.asBillBean());
        notifyJournalBecauseOfSammelReceipt(sammelReceipt);
        return billBean;
    }

    private Receipt createNullReceipt() {
        return receiptCreator.createReceipt(nullReceiptCreator.createNullSale());
    }

    private void notifyCompany(BillBean billBean) {
        sendMailEvent.fire(
            new SendMailEvent(
                billBean.getCashBoxID() + ": signature-device is available again",
                billBeanToMailContentConverter.convertToMailContent(billBean)));
    }

    private void notifyFinancialOffice() {
        signatureDeviceAvailableAgainEvent.fire(new SignatureDeviceAvailableAgainEvent());
    }

    private void notifyJournal() {
        cashboxJournalEvent
            .fire(new CashboxJournalEvent("signature-device " + cashBox.getSignatureCertificateSerialNumber().get() + " is available again", cashBox));
    }

    private void notifyJournalBecauseOfSammelReceipt(Receipt receipt) {
        cashboxJournalEvent.fire(
            new CashboxJournalEvent(
                "create receipt " + receipt.getReceiptId().get() + ", " + receipt.getReceiptType().getName().get(),
                receipt.getCashBox(),
                receipt.getReceiptDate().get()));
    }
}