package org.regkas.service.core.receipt;

import java.math.BigDecimal;
import java.util.Optional;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.boatpos.common.domain.api.values.SimpleBigDecimalObject;
import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.domain.api.builder.ReceiptBuilder;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Company;
import org.regkas.domain.api.model.Product;
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.User;
import org.regkas.domain.api.repository.ProductRepository;
import org.regkas.domain.api.repository.ReceiptElementRepository;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.domain.api.signature.RkOnlineResourceFactory;
import org.regkas.domain.api.values.Amount;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.ReceiptDate;
import org.regkas.domain.api.values.SuiteId;
import org.regkas.domain.api.values.TotalPrice;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.SaleBean;

import com.google.common.collect.Lists;

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
                TotalPrice totalPriceForElement = new TotalPrice(round(receiptElementBean.getTotalPrice()));
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

    @SuppressWarnings("WeakerAccess")
    public Receipt createSammelReceipt() {
        return createReceipt(new SaleBean("cash", "Sammel-Beleg", Lists.newArrayList()));
    }

    public Receipt createDayReceipt() {
        return createReceipt(new SaleBean("cash", "Tages-Beleg", Lists.newArrayList()));
    }

    @SuppressWarnings("WeakerAccess")
    public Receipt createMonthReceipt() {
        return createReceipt(new SaleBean("cash", "Monats-Beleg", Lists.newArrayList()));
    }

    @SuppressWarnings("WeakerAccess")
    public Receipt createYearReceipt() {
        return createReceipt(new SaleBean("cash", "Jahres-Beleg", Lists.newArrayList()));
    }

    private BigDecimal round(BigDecimal price) {
        return scalePrice(price.setScale(1, BigDecimal.ROUND_HALF_UP));
    }

    private BigDecimal scalePrice(BigDecimal price) {
        return price.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
