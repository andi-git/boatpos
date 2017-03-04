package org.regkas.service.core.email;

import javax.enterprise.context.ApplicationScoped;

import org.regkas.service.api.bean.BillBean;

@ApplicationScoped
public class BillBeanToMailContentConverter {

    public String convertToMailContent(BillBean billBean) {
        return "cash-box: " +
            billBean.getCashBoxID() +
            "\n" +
            "receipt-id: " +
            billBean.getReceiptIdentifier() +
            "\n" +
            "receipt-time: " +
            billBean.getReceiptDateAndTime() +
            "\n" +
            "receipt-type: " +
            billBean.getReceiptType();
    }
}
