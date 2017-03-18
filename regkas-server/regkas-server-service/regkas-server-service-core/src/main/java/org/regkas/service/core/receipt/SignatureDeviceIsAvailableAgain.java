package org.regkas.service.core.receipt;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.regkas.repository.api.model.Receipt;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.core.email.BillBeanToMailContentConverter;
import org.regkas.service.core.email.SendMailEvent;
import org.regkas.service.core.financialoffice.SignatureDeviceAvailableAgainEvent;

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
        Receipt nullReceipt = createNullReceipt();
        billBean.setSammelBeleg(nullReceipt.asBillBean());
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
}
