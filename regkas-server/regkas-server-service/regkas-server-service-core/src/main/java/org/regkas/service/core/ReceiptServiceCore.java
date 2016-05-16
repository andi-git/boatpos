package org.regkas.service.core;

import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.values.DEPString;
import org.regkas.service.api.ReceiptService;
import org.regkas.service.core.serializer.NonPrettyPrintingGson;
import org.regkas.service.core.serializer.Serializer;
import org.regkas.service.core.util.ReceiptToBillConverter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class ReceiptServiceCore implements ReceiptService {

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    @Inject
    private ReceiptToBillConverter receiptToBillConverter;

    @Override
    public int updateReceipts() {
        List<Receipt> receipts = receiptRepository.loadAllWithoutDEP();
        int count = 0;
        for (Receipt receipt : receipts) {
            receipt.setDEP(new DEPString(serializer.serialize(receiptToBillConverter.convert(receipt)))).persist();
            count++;
        }
        return count;
    }
}
