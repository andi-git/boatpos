package org.regkas.service.core.receipt;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.model.ReceiptTypeStart;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.domain.api.signature.RkOnlineResourceFactory;
import org.regkas.domain.api.signature.SignatureDeviceMandatoryException;
import org.regkas.service.core.email.ReceiptToMailContentConverter;
import org.regkas.service.core.email.SendMailEvent;
import org.regkas.service.core.financialoffice.SignatureDeviceAvailableAgainEvent;
import org.regkas.service.core.journal.CashboxJournalEvent;

@ApplicationScoped
public class SignatureDeviceIsAvailableAgain implements HandleSignatureDeviceAvailability {

    @Inject
    private ReceiptCreator receiptCreator;

    @Inject
    private ReceiptToMailContentConverter receiptToMailContentConverter;

    @Inject
    private Event<SendMailEvent> sendMailEvent;

    @Inject
    private Event<SignatureDeviceAvailableAgainEvent> signatureDeviceAvailableAgainEvent;

    @Inject
    private Event<CashboxJournalEvent> cashboxJournalEvent;

    @Inject
    @Current
    private CashBox cashBox;

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Override
    public boolean canHandle(Receipt currentReceipt, Receipt lastReceipt) {
        return SimpleValueObject.nullSafe(currentReceipt.getSignatureDeviceAvailable()) &&
            !SimpleValueObject.nullSafe(lastReceipt.getSignatureDeviceAvailable()) &&
            !(currentReceipt.getReceiptType() instanceof ReceiptTypeStart);
    }

    @Override
    public Receipt handle(Receipt receipt) {
        checkNotNull(receipt, "'receipt' must not be null");
        Optional<Receipt> lastReceiptWhereSignatureDeviceWasAvailable = receiptRepository
            .loadLastWithSignatureDeviceAvailableBefore(receipt.getReceiptDate().get(), cashBox);
        if (lastReceiptWhereSignatureDeviceWasAvailable.isPresent()) {
            Optional<Receipt> firstWhereSignatureDeviceWasNotAvailable = receiptRepository
                .loadFirstWhereSignatureDeviceIsNotAvailableAfter(lastReceiptWhereSignatureDeviceWasAvailable.get().getReceiptDate().get(), cashBox);
            if (firstWhereSignatureDeviceWasNotAvailable.isPresent()) {
                Optional<Receipt> sammelReceipt = Optional.empty();
                try {
                    sammelReceipt = Optional.of(receiptCreator.createSammelReceipt());
                } catch (SignatureDeviceMandatoryException e) {
                    log.error("signature device is not available when creating Sammel-Beleg");
                }
                if (sammelReceipt.isPresent()) {
                    sammelBelegIsPresent(receipt, firstWhereSignatureDeviceWasNotAvailable.get(), sammelReceipt.get());
                } else {
                    sammelBelegIsNotPresent(receipt);
                }
                return receipt;
            } else {
                throw new RuntimeException(
                    "first receipt where signature device was damaged not present, after " +
                        lastReceiptWhereSignatureDeviceWasAvailable.get().getReceiptDate().get());
            }
        } else {
            throw new RuntimeException("last receipt where signature device was available not present");
        }
    }

    private void sammelBelegIsPresent(Receipt receipt, Receipt firstWhereSignatureDeviceWasNotAvailable, Receipt sammelReceipt) {
        notifySignatureDeviceIsAvailableAgain(receipt);
        receipt.setSammelBeleg(sammelReceipt);
        receipt.setSammelBelegStart(firstWhereSignatureDeviceWasNotAvailable.getReceiptDate().get());
        receipt.setSammelBelegEnd(sammelReceipt.getReceiptDate().get());
        notifyJournalBecauseOfSammelReceipt(sammelReceipt);
    }

    private void sammelBelegIsNotPresent(Receipt receipt) {
        notifySigantureDeviceIsNotAvailableOnSammelbeleg(receipt);
        receipt
            .setCompactJWSRepresentation(
                rkOnlineResourceFactory
                    .getRkOnlineResourceSignature()
                    .signWhenSignatureIsNotAvailable(receipt.getDataToBeSigned(), receipt.getReceiptType()))
            .persist();
    }

    private void notifySigantureDeviceIsNotAvailableOnSammelbeleg(Receipt receiptBefore) {
        notifyJournalOfSignatureDeviceIsNotAvailableOnSammelbeleg(receiptBefore);
        notifyCompanyOfSignatureDeviceIsNotAvailableOnSammelbeleg(receiptBefore);
    }

    private void notifyCompanyOfSignatureDeviceIsNotAvailableOnSammelbeleg(Receipt receiptBefore) {
        sendMailEvent.fire(
            new SendMailEvent(
                "signature device damaged on Sammel-Beleg",
                "signature-device not available when creating Sammel-Beleg after " + receiptBefore.getReceiptId().get()));
    }

    private void notifyJournalOfSignatureDeviceIsNotAvailableOnSammelbeleg(Receipt receiptBefore) {
        cashboxJournalEvent.fire(
            new CashboxJournalEvent(
                "signature-device not available when creating Sammel-Beleg after " + receiptBefore.getReceiptId().get(),
                cashBox));
    }

    private void notifySignatureDeviceIsAvailableAgain(Receipt receipt) {
        notifyCompany(receipt);
        notifyFinancialOffice();
        notifyJournal();
    }

    private void notifyCompany(Receipt receipt) {
        sendMailEvent.fire(
            new SendMailEvent(
                receipt.getCashBox().getName().get() + ": signature-device is available again",
                receiptToMailContentConverter.convertToMailContent(receipt)));
    }

    private void notifyFinancialOffice() {
        signatureDeviceAvailableAgainEvent.fire(new SignatureDeviceAvailableAgainEvent());
    }

    private void notifyJournal() {
        cashboxJournalEvent.fire(
            new CashboxJournalEvent("signature-device " + cashBox.getSignatureCertificateSerialNumber().get() + " is available again", cashBox));
    }

    private void notifyJournalBecauseOfSammelReceipt(Receipt receipt) {
        cashboxJournalEvent.fire(
            new CashboxJournalEvent(
                "create receipt " + receipt.getReceiptId().get() + ", " + receipt.getReceiptType().getName().get(),
                receipt.getCashBox(),
                receipt.getReceiptDate().get()));
    }
}
