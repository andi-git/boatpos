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
import org.regkas.service.core.util.ReceiptIdCalculator;
import org.regkas.service.core.util.TaxSet;

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
    private CompanyRepository companyRepository;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private UserRepository userRepository;

    @Override
    public BillBean sale(SaleBean sale) {
        ReceiptBuilder receiptBuilder = receiptRepository.builder()
                .add(receiptIdCalculator.getNextReceiptId())
                .add(new ReceiptDate(dateTimeHelper.currentTime()))
                .add(new EncryptedTurnoverValue(""))
                .add(new SignatureValuePreviousReceipt(""))
                .add(PaymentMethod.get(sale.getPaymentMethod()))
                .add(TimeType.Current)
                .add(receiptTypeRepository.loadBy(new Name(sale.getReceiptType())).orElseGet(() -> receiptTypeRepository.loadNullType()))
                .add(cashBox)
                .add(user)
                .add(company);
        for (ReceiptElementBean receiptElementBean : sale.getSaleElements()) {
            Optional<Product> productOptional = productRepository.loadBy(new Name(receiptElementBean.getProduct().getName()));
            if (!productOptional.isPresent()) {
                throw new RuntimeException("unable to get " + Product.class.getName() + " with name" + receiptElementBean.getProduct().getName());
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

        BillBean bill = new BillBean();
        bill.setCompany(company.asDto());
        bill.setCashBoxID(cashBox.getName().get());
        bill.setReceiptIdentifier(receipt.getReceiptId().get());
        bill.setReceiptDateAndTime(receipt.getReceiptDate().get());
        bill.setEncryptedTurnoverValue("");
        bill.setSignatureCertificateSerialNumber("");
        bill.setSignatureValuePreviousReceipt("");
        for (ReceiptElement receiptElement : receipt.getReceiptElements()) {
            TaxSet.addToBill(receiptElement, bill);
        }
        return bill;
    }
}
