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
import org.regkas.service.core.financialoffice.SignatureDeviceDamagedEvent;
import org.regkas.service.core.journal.CashboxJournalEvent;

@ApplicationScoped
public class SignatureDeviceIsDamagedFirstTime implements HandleSignatureDeviceAvailability {

    @Inject
    private Event<SendMailEvent> sendMailEvent;

    @Inject
    private BillBeanToMailContentConverter billBeanToMailContentConverter;

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
    public BillBean handle(BillBean billBean) {
        checkNotNull(billBean, "'billBean' must not be null");
        notifyCompany(billBean);
        notifyFinancialOffice();
        notifyJournal();
        return billBean;
    }

    private void notifyCompany(BillBean billBean) {
        sendMailEvent.fire(
            new SendMailEvent(
                billBean.getCashBoxID() + ": signature-device is not available",
                billBeanToMailContentConverter.convertToMailContent(billBean)));
    }

    private void notifyFinancialOffice() {
        signatureDeviceDamagedEvent.fire(new SignatureDeviceDamagedEvent());
    }

    private void notifyJournal() {
        cashboxJournalEvent
            .fire(new CashboxJournalEvent("signature-device " + cashBox.getSignatureCertificateSerialNumber().get() + " is damaged", cashBox));
    }
}
