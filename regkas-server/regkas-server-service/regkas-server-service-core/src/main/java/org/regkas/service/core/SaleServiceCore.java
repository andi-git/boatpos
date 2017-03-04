package org.regkas.service.core;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.SaleBean;
import org.regkas.service.core.receipt.HandleSignatureDeviceAvailability;
import org.regkas.service.core.receipt.ReceiptCreator;

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

    @Override
    public BillBean sale(SaleBean sale) {
        Optional<Receipt> lastReceiptOptional = receiptRepository.loadLastReceipt(cashBox);
        Receipt receipt = receiptCreator.createReceipt(sale);
        BillBean billBean = receipt.asBillBean();
        Optional<HandleSignatureDeviceAvailability> handleSignatureDeviceAvailability = getHandleSignatureDeviceAvailability(
            receipt,
            lastReceiptOptional);
        if (handleSignatureDeviceAvailability.isPresent()) {
            billBean = handleSignatureDeviceAvailability.get().handle(billBean);
        }
        return billBean;
    }

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
}
