package org.regkas.service.core.receipt;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.regkas.repository.api.model.Receipt;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.core.email.BillBeanToMailContentConverter;
import org.regkas.service.core.email.MailSenderFactory;

@ApplicationScoped
public class SignatureDeviceIsStillDamaged implements HandleSignatureDeviceAvailability {

    @Inject
    private MailSenderFactory mailSenderFactory;

    @Inject
    private BillBeanToMailContentConverter billBeanToMailContentConverter;

    @Override
    public boolean canHandle(Receipt currentReceipt, Receipt lastReceipt) {
        return !SimpleValueObject.nullSafe(currentReceipt.getSignatureDeviceAvailable()) &&
            !SimpleValueObject.nullSafe(lastReceipt.getSignatureDeviceAvailable());
    }

    @Override
    public BillBean handle(BillBean billBean) {
        checkNotNull(billBean, "'billBean' must not be null");
        notifyCompany(billBean);
        return billBean;
    }

    private void notifyCompany(BillBean billBean) {
        mailSenderFactory.getMailSender().send(
            billBean.getCashBoxID() + ": signature-device is still no available",
            billBeanToMailContentConverter.convertToMailContent(billBean));
    }
}
