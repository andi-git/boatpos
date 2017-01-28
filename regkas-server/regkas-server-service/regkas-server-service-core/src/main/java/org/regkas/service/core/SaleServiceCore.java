package org.regkas.service.core;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.model.TimeType;
import org.regkas.repository.api.builder.ReceiptBuilder;
import org.regkas.repository.api.model.*;
import org.regkas.repository.api.repository.*;
import org.regkas.repository.api.values.*;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.SaleBean;
import org.regkas.service.core.receipt.ReceiptIdCalculator;
import org.regkas.service.core.receipt.ReceiptToBillConverter;
import org.regkas.service.core.receipt.ReceiptTypeConverter;
import org.regkas.service.core.serializer.NonPrettyPrintingGson;
import org.regkas.service.core.serializer.Serializer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Optional;

@RequestScoped
public class SaleServiceCore implements SaleService {

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private ReceiptIdCalculator receiptIdCalculator;

    @Inject
    private ReceiptTypeRepository receiptTypeRepository;

    @Inject
    private ReceiptElementRepository receiptElementRepository;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Inject
    @Current
    private CashBox cashBox;

    @Inject
    @Current
    private User user;

    @Inject
    @Current
    private Company company;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ReceiptToBillConverter receiptToBillConverter;

    @Inject
    private ReceiptTypeConverter receiptTypeConverter;

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    @Override
    public BillBean sale(SaleBean sale) {
        ReceiptType receiptType = receiptTypeConverter.convertToReceiptType(new Name(sale.getReceiptType()));
        ReceiptBuilder receiptBuilder = receiptRepository.builder()
                .add(receiptIdCalculator.getNextReceiptId())
                .add(new ReceiptDate(dateTimeHelper.currentTime()))
                .add(new EncryptedTurnoverValue(""))
                .add(new SignatureValuePreviousReceipt(""))
                .add(PaymentMethod.get(sale.getPaymentMethod()))
                .add(TimeType.Current)
                .add(receiptType)
                .add(cashBox)
                .add(user)
                .add(company);
        for (ReceiptElementBean receiptElementBean : sale.getSaleElements()) {
            Optional<Product> productOptional = productRepository.loadBy(new Name(receiptElementBean.getProduct().getName()), cashBox);
            if (!productOptional.isPresent()) {
                throw new RuntimeException("unable to get " + Product.class.getName() + " with name '" + receiptElementBean.getProduct().getName() + "'");
            } else {
                receiptBuilder.add(receiptElementRepository
                        .builder()
                        .add(new Amount(receiptElementBean.getAmount()))
                        .add(new TotalPrice(receiptElementBean.getTotalPrice()))
                        .add(productOptional.get())
                        .build());
            }
        }

        Receipt receipt = receiptBuilder.build().persist();
        BillBean bill = receiptToBillConverter.convert(receipt);
        receipt.setDEP(new DEPString(serializer.serialize(bill))).persist();
        return bill;
    }
}
