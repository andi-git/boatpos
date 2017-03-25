package org.regkas.service.core.receipt;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Receipt;
import org.regkas.service.core.email.ReceiptToMailContentConverter;
import org.regkas.service.core.email.SendMailEvent;
import org.regkas.service.core.financialoffice.SignatureDeviceDamagedEvent;
import org.regkas.service.core.journal.CashboxJournalEvent;

@ApplicationScoped
public class SignatureDeviceIsDamagedFirstTime implements HandleSignatureDeviceAvailability {

    @Inject
    private Event<SendMailEvent> sendMailEvent;

    @Inject
    private ReceiptToMailContentConverter receiptToMailContentConverter;

    @Inject
    private Event<SignatureDeviceDamagedEvent> signatureDeviceDamagedEvent;

    @Inject
    private Event<CashboxJournalEvent> cashboxJournalEvent;

    @Inject
    @Current
    private CashBox cashBox;

    @Override
    public boolean canHandle(Receipt currentReceipt, Receipt lastReceipt) {
        return !SimpleValueObject.nullSafe(currentReceipt.getSignatureDeviceAvailable()) &&
            SimpleValueObject.nullSafe(lastReceipt.getSignatureDeviceAvailable());
    }

    @Override
    public Receipt handle(Receipt receipt) {
        checkNotNull(receipt, "'receipt' must not be null");
        notifyCompany(receipt);
        notifyFinancialOffice();
        notifyJournal();
        return receipt;
    }

    private void notifyCompany(Receipt receipt) {
        sendMailEvent.fire(
            new SendMailEvent(
                receipt.getCashBox().getName().get() + ": signature-device is not available",
                receiptToMailContentConverter.convertToMailContent(receipt)));
    }

    private void notifyFinancialOffice() {
        signatureDeviceDamagedEvent.fire(new SignatureDeviceDamagedEvent());
    }

    private void notifyJournal() {
        cashboxJournalEvent
            .fire(new CashboxJournalEvent("signature-device " + cashBox.getSignatureCertificateSerialNumber().get() + " is damaged", cashBox));
    }
}
