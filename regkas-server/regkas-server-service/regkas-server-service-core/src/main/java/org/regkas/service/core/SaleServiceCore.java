package org.regkas.service.core;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.repository.api.values.SimpleBigDecimalObject;
import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.builder.ReceiptBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.repository.ProductRepository;
import org.regkas.repository.api.repository.ReceiptElementRepository;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.repository.ReceiptTypeRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.serializer.NonPrettyPrintingGson;
import org.regkas.repository.api.serializer.Serializer;
import org.regkas.repository.api.values.Amount;
import org.regkas.repository.api.values.DEPString;
import org.regkas.repository.api.values.EncryptedTurnoverValue;
import org.regkas.repository.api.values.JWSCompactRepresentation;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.ReceiptDate;
import org.regkas.repository.api.values.SignatureValuePreviousReceipt;
import org.regkas.repository.api.values.SuiteId;
import org.regkas.repository.api.values.TotalPrice;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.SaleBean;
import org.regkas.service.core.receipt.ReceiptIdCalculator;
import org.regkas.service.core.receipt.ReceiptTypeConverter;

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
    private ReceiptTypeConverter receiptTypeConverter;

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Override
    public BillBean sale(SaleBean sale) {
        ReceiptType receiptType = receiptTypeConverter.convertToReceiptType(new Name(sale.getReceiptType()));

        Receipt receipt = buildReceiptBasedOnSaleBean(sale, receiptType);

        updateTurnoverCounter(receiptType, receipt.getTotalPrice());
        encryptTurnoverCounter(receiptType, receipt);
        setSignatureValueOfPreviousReceipt(receiptType, receipt);
        signReceipt(receipt);

        BillBean bill = receipt.asBillBean();
        receipt.setDEP(new DEPString(serializer.serialize(bill)));

        receipt.persist();

        return bill;
    }

    private Receipt buildReceiptBasedOnSaleBean(SaleBean sale, ReceiptType receiptType) {
        ReceiptBuilder receiptBuilder = receiptRepository.builder()
                .add(receiptIdCalculator.getNextReceiptId())
                .add(new ReceiptDate(dateTimeHelper.currentTime()))
                .add(new EncryptedTurnoverValue(""))
                .add(new SignatureValuePreviousReceipt(""))
                .add(PaymentMethod.get(sale.getPaymentMethod()))
                .add(receiptType)
                .add(cashBox)
                .add(user)
                .add(company)
                .add(new SuiteId(cashBox.getCertificationServiceProvider()));
        TotalPrice totalPrice = new TotalPrice(SimpleBigDecimalObject.ZERO);
        for (ReceiptElementBean receiptElementBean : sale.getSaleElements()) {
            Optional<Product> productOptional = productRepository.loadBy(new Name(receiptElementBean.getProduct().getName()), cashBox);
            if (!productOptional.isPresent()) {
                throw new RuntimeException("unable to get " + Product.class.getName() + " with name '" + receiptElementBean.getProduct().getName() + "'");
            } else {
                TotalPrice totalPriceForElement = new TotalPrice(receiptElementBean.getTotalPrice());
                totalPrice = totalPrice.add(totalPriceForElement);
                receiptBuilder.add(receiptElementRepository
                        .builder()
                        .add(new Amount(receiptElementBean.getAmount()))
                        .add(totalPriceForElement)
                        .add(productOptional.get())
                        .build());
            }
        }
        receiptBuilder.add(totalPrice);
        return receiptBuilder.build();
    }

    private void setSignatureValueOfPreviousReceipt(ReceiptType receiptType, Receipt receipt) {
        receipt.setSignatureValuePreviousReceipt(receiptType.calculateChainValue(cashBox));
    }

    private void encryptTurnoverCounter(ReceiptType receiptType, Receipt receipt) {
        receipt.setEncryptedTurnoverValue(receiptType.getEncryptTurnoverCounter().encryptTurnoverCounter(receipt.getReceiptId(), cashBox));
    }

    private void updateTurnoverCounter(ReceiptType receiptType, TotalPrice totalPrice) {
        receiptType.getUpdateTurnoverCounter().updateTurnOver(cashBox, totalPrice);
    }

    private void signReceipt(Receipt receipt) {
        receipt.setJWSCompactRepresentation(new JWSCompactRepresentation("")); // TODO implement this
    }
}
