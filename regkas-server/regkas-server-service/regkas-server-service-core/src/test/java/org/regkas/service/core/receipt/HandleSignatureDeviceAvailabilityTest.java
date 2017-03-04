package org.regkas.service.core.receipt;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Version;
import org.junit.After;
import org.junit.Before;
import org.regkas.model.ReceiptEntity;
import org.regkas.repository.api.context.CashBoxContext;
import org.regkas.repository.api.context.CompanyContext;
import org.regkas.repository.api.context.UserContext;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.CompanyRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.signature.CompactJWSRepresentation;
import org.regkas.repository.api.values.DEPString;
import org.regkas.repository.api.values.EncryptedTurnoverValue;
import org.regkas.repository.api.values.JWSPayload;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.ReceiptDate;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.repository.api.values.ReceiptMachineReadableRepresentation;
import org.regkas.repository.api.values.SignatureDeviceAvailable;
import org.regkas.repository.api.values.SignatureValuePreviousReceipt;
import org.regkas.repository.api.values.SuiteId;
import org.regkas.repository.api.values.TotalPrice;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.ReceiptBean;
import org.regkas.service.core.email.MailSenderFactory;
import org.regkas.service.core.email.MailSenderMock;
import org.regkas.service.core.financialoffice.FinancialOfficeSenderFactory;
import org.regkas.service.core.financialoffice.FinancialOfficeSenderMock;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.util.List;

public abstract class HandleSignatureDeviceAvailabilityTest extends EntityManagerProviderForRegkas {

    @Inject
    private MailSenderFactory mailSenderFactory;

    @Inject
    private FinancialOfficeSenderFactory financialOfficeSenderFactory;

    protected MailSenderMock mailSenderMock = new MailSenderMock();

    protected FinancialOfficeSenderMock financialOfficeSenderMock = new FinancialOfficeSenderMock();

    protected final Receipt receiptSignatureDeviceAvailable = new ReceiptMock(true);

    protected final Receipt receiptSignatureDeviceNotAvailable = new ReceiptMock(false);

    @Inject
    private CompanyRepository companyRepository;

    @Inject
    private CompanyContext companyContext;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserContext userContext;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private CashBoxContext cashBoxContext;

    @Before
    public void before() {
        mailSenderMock.reset();
        mailSenderFactory.setMailSender(mailSenderMock);
        financialOfficeSenderMock.reset();
        financialOfficeSenderFactory.setFinancialOfficeSender(financialOfficeSenderMock);
        companyContext.set(companyRepository.loadBy(new Name("company")));
        userContext.set(userRepository.loadBy(new Name("Maria Musterfrau")));
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
    }

    @After
    public void after() {
        companyContext.clear();
        userContext.clear();
        cashBoxContext.clear();
        mailSenderFactory.reset();
        financialOfficeSenderFactory.reset();
    }

    public static class ReceiptMock implements Receipt {

        private final boolean signatureDeviceAvailable;

        public ReceiptMock(boolean signatureDeviceAvailable) {
            this.signatureDeviceAvailable = signatureDeviceAvailable;
        }

        @Override
        public Receipt persist() {
            return null;
        }

        @Override
        public void delete() {

        }

        @Override
        public ReceiptEntity asEntity() {
            return null;
        }

        @Override
        public DomainId getId() {
            return null;
        }

        @Override
        public Version getVersion() {
            return null;
        }

        @Override
        public ReceiptBean asDto() {
            return null;
        }

        @Override
        public ReceiptId getReceiptId() {
            return null;
        }

        @Override
        public Receipt setReceiptId(ReceiptId receiptId) {
            return null;
        }

        @Override
        public ReceiptDate getReceiptDate() {
            return null;
        }

        @Override
        public Receipt setReceiptDate(ReceiptDate receiptDate) {
            return null;
        }

        @Override
        public EncryptedTurnoverValue getEncryptedTurnoverValue() {
            return null;
        }

        @Override
        public Receipt setEncryptedTurnoverValue(EncryptedTurnoverValue encryptedTurnoverValue) {
            return null;
        }

        @Override
        public SignatureValuePreviousReceipt getSignatureValuePreviousReceipt() {
            return null;
        }

        @Override
        public Receipt setSignatureValuePreviousReceipt(SignatureValuePreviousReceipt signatureValuePreviousReceipt) {
            return null;
        }

        @Override
        public Company getCompany() {
            return null;
        }

        @Override
        public Receipt setCompany(Company company) {
            return null;
        }

        @Override
        public User getUser() {
            return null;
        }

        @Override
        public Receipt setUser(User user) {
            return null;
        }

        @Override
        public ReceiptType getReceiptType() {
            return null;
        }

        @Override
        public Receipt setReceiptType(ReceiptType receiptType) {
            return null;
        }

        @Override
        public CashBox getCashBox() {
            return null;
        }

        @Override
        public Receipt setCashBox(CashBox cashBox) {
            return null;
        }

        @Override
        public PaymentMethod getPaymentMethod() {
            return null;
        }

        @Override
        public Receipt setPaymentMethod(PaymentMethod paymentMethod) {
            return null;
        }

        @Override
        public DEPString getDEP() {
            return null;
        }

        @Override
        public Receipt setDEP(DEPString dep) {
            return null;
        }

        @Override
        public TotalPrice getTotalPrice() {
            return null;
        }

        @Override
        public Receipt setTotalPrice(TotalPrice totalPrice) {
            return null;
        }

        @Override
        public <T extends TaxSet> TotalPrice getTotalPriceAfterTaxOf(Class<T> taxSet) {
            return null;
        }

        @Override
        public <T extends TaxSet> TotalPrice getTotalTaxOf(Class<T> taxSet) {
            return null;
        }

        @Override
        public <T extends TaxSet> TotalPrice getTotalPriceBeforeTaxOf(Class<T> taxSet) {
            return null;
        }

        @Override
        public SuiteId getSuiteId() {
            return null;
        }

        @Override
        public Receipt setSuiteId(SuiteId suiteId) {
            return null;
        }

        @Override
        public List<ReceiptElement> getReceiptElements() {
            return null;
        }

        @Override
        public Receipt addReceiptElements(List<ReceiptElement> receiptElements) {
            return null;
        }

        @Override
        public Receipt addReceiptElement(ReceiptElement receiptElement) {
            return null;
        }

        @Override
        public Receipt clearReceiptElements() {
            return null;
        }

        @Override
        public JWSPayload getDataToBeSigned() {
            return null;
        }

        @Override
        public BillBean asBillBean() {
            return null;
        }

        @Override
        public Receipt setCompactJWSRepresentation(CompactJWSRepresentation compactJwsRepresentation) {
            return null;
        }

        @Override
        public CompactJWSRepresentation getCompactJwsRepresentation() {
            return null;
        }

        @Override
        public SignatureDeviceAvailable getSignatureDeviceAvailable() {
            return new SignatureDeviceAvailable(signatureDeviceAvailable);
        }

        @Override
        public ReceiptMachineReadableRepresentation getReceiptMachineReadableRepresentation() {
            return null;
        }

        @Override
        public Receipt persistWithoutCreatingDEP() {
            return null;
        }
    }

}
