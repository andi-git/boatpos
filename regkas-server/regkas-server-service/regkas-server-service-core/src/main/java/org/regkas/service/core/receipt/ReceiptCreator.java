package org.regkas.service.core.receipt;

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
import org.regkas.repository.api.signature.RkOnlineResourceFactory;
import org.regkas.repository.api.values.Amount;
import org.regkas.repository.api.values.EncryptedTurnoverValue;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.ReceiptDate;
import org.regkas.repository.api.values.SignatureValuePreviousReceipt;
import org.regkas.repository.api.values.SuiteId;
import org.regkas.repository.api.values.TotalPrice;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.SaleBean;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Optional;

@Dependent
public class ReceiptCreator {

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private ReceiptIdCalculatorFactory receiptIdCalculatorFactory;

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
    private ReceiptTypeConverter receiptTypeConverter;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    public Receipt createReceipt(SaleBean sale) {
        ReceiptType receiptType = receiptTypeConverter.convertToReceiptType(new Name(sale.getReceiptType()));
        Receipt receipt = buildReceiptBasedOnSaleBean(sale, receiptType);
        updateTurnoverCounter(receiptType, receipt.getTotalPrice());
        encryptTurnoverCounter(receiptType, receipt);
        setSignatureValueOfPreviousReceipt(receiptType, receipt);
        signReceipt(receipt);
        return receipt.persist();
    }

    private Receipt buildReceiptBasedOnSaleBean(SaleBean sale, ReceiptType receiptType) {
        ReceiptBuilder receiptBuilder = receiptRepository
            .builder()
            .add(receiptIdCalculatorFactory.get().getNextReceiptId())
            .add(new ReceiptDate(dateTimeHelper.currentTime()))
            .add(PaymentMethod.get(sale.getPaymentMethod()))
            .add(receiptType)
            .add(cashBox)
            .add(user)
            .add(company)
            .add(new SuiteId(cashBox.getCertificationServiceProvider()));
        TotalPrice totalPrice = new TotalPrice(SimpleBigDecimalObject.ZERO);
        for (ReceiptElementBean receiptElementBean : sale.getSaleElements()) {
            Optional<Product> productOptional = productRepository.loadBy(new Name(receiptElementBean.getProduct().getName()), cashBox);
            if ( !productOptional.isPresent()) {
                throw new RuntimeException(
                    "unable to get " + Product.class.getName() + " with name '" + receiptElementBean.getProduct().getName() + "'");
            } else {
                TotalPrice totalPriceForElement = new TotalPrice(receiptElementBean.getTotalPrice());
                totalPrice = totalPrice.add(totalPriceForElement);
                receiptBuilder.add(
                    receiptElementRepository
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
        receipt.setCompactJWSRepresentation(
            rkOnlineResourceFactory.getRkOnlineResourceSignature().sign(receipt.getDataToBeSigned(), receipt.getReceiptType()));
    }
}
