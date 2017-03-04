package org.regkas.service.core.receipt;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.regkas.repository.api.model.Receipt;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.core.email.BillBeanToMailContentConverter;
import org.regkas.service.core.email.MailSenderFactory;
import org.regkas.service.core.financialoffice.FinancialOfficeSenderFactory;

@ApplicationScoped
public class SignatureDeviceIsAvailableAgain implements HandleSignatureDeviceAvailability {

    @Inject
    private NullReceiptCreator nullReceiptCreator;

    @Inject
    private ReceiptCreator receiptCreator;

    @Inject
    private MailSenderFactory mailSenderFactory;

    @Inject
    private BillBeanToMailContentConverter billBeanToMailContentConverter;

    @Inject
    private FinancialOfficeSenderFactory financialOfficeSenderFactory;

    @Override
    public boolean canHandle(Receipt currentReceipt, Receipt lastReceipt) {
        return SimpleValueObject.nullSafe(currentReceipt.getSignatureDeviceAvailable()) &&
            !SimpleValueObject.nullSafe(lastReceipt.getSignatureDeviceAvailable());
    }

    @Override
    public BillBean handle(BillBean billBean) {
        checkNotNull(billBean, "'billBean' must not be null");
        notifyCompany(billBean);
        notifyFinancialOffice(billBean);
        Receipt nullReceipt = createNullReceipt();
        billBean.setNullBill(nullReceipt.asBillBean());
        return billBean;
    }

    private Receipt createNullReceipt() {
        return receiptCreator.createReceipt(nullReceiptCreator.createNullSale());
    }

    private void notifyCompany(BillBean billBean) {
        mailSenderFactory.getMailSender().send(
            billBean.getCashBoxID() + ": signature-device is available again",
            billBeanToMailContentConverter.convertToMailContent(billBean));
    }

    private void notifyFinancialOffice(BillBean billBean) {
        financialOfficeSenderFactory.getFinancialOfficeSender().signatureDeviceAvailableAgain();
    }
}
