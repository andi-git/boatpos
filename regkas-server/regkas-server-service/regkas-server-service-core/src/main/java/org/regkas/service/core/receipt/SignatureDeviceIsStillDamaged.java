package org.regkas.service.core.receipt;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.regkas.domain.api.model.Receipt;
import org.regkas.service.core.email.ReceiptToMailContentConverter;
import org.regkas.service.core.email.SendMailEvent;

@ApplicationScoped
public class SignatureDeviceIsStillDamaged implements HandleSignatureDeviceAvailability {

    @Inject
    private Event<SendMailEvent> sendMailEvent;

    @Inject
    private ReceiptToMailContentConverter receiptToMailContentConverter;

    @Override
    public boolean canHandle(Receipt currentReceipt, Receipt lastReceipt) {
        return !SimpleValueObject.nullSafe(currentReceipt.getSignatureDeviceAvailable()) &&
            !SimpleValueObject.nullSafe(lastReceipt.getSignatureDeviceAvailable());
    }

    @Override
    public Receipt handle(Receipt receipt) {
        checkNotNull(receipt, "'receipt' must not be null");
        notifyCompany(receipt);
        return receipt;
    }

    private void notifyCompany(Receipt receipt) {
        sendMailEvent.fire(
            new SendMailEvent(
                receipt.getCashBox().getName().get() + ": signature-device is still no available",
                receiptToMailContentConverter.convertToMailContent(receipt)));
    }
}
