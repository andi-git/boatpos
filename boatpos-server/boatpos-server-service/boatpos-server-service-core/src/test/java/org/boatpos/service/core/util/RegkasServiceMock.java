package org.boatpos.service.core.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

import org.boatpos.domain.api.values.ReceiptId;
import org.boatpos.service.api.bean.PaymentBean;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.CompanyBean;
import org.regkas.service.api.bean.IncomeBean;

@ApplicationScoped
@Specializes
public class RegkasServiceMock extends RegkasService {

    private IncomeBean mockIncomeBean = new IncomeBean();

    @Override
    public BillBean sale(PaymentBean paymentBean) {
        return mockBillBean(paymentBean.getValue());
    }

    private BillBean mockBillBean(BigDecimal value) {
        return new BillBean(
                new CompanyBean(),
                "cashboxId",
                "receipt-id",
                LocalDateTime.now(),
                value,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                new ArrayList<>(),
                value,
                "Standard-Beleg",
                "",
                false);
    }

    public void setMockIncomeBean(IncomeBean mockIncomeBean) {
        this.mockIncomeBean = mockIncomeBean;
    }

    public void resetMockIncomeBean() {
        this.mockIncomeBean = new IncomeBean();
    }

    @Override
    public IncomeBean totalIncome(int year) {
        return mockIncomeBean;
    }

    @Override
    public IncomeBean totalIncome(int year, int month) {
        return mockIncomeBean;
    }

    @Override
    public IncomeBean totalIncome(int year, int month, int day) {
        return mockIncomeBean;
    }

    @Override
    public File getDEP(int year) {
        return createFile("depYear");
    }

    @Override
    public File getDEP(int year, int month) {
        return createFile("depYearMonth");
    }

    @Override
    public File getDEP(int year, int month, int day) {
        return createFile("depYearMonthDay");
    }

    @Override
    public File getDEPRKSV() {
        return createFile("depRKSV");
    }

    private File createFile(String fileName) {
        File file = new File(System.getProperty("java.io.tmpdir"), fileName);
        try {
            // noinspection ResultOfMethodCallIgnored
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    @Override
    public Boolean isSignatureDeviceAvailable() {
        return true;
    }

    @Override
    public BillBean receiptById(ReceiptId receiptId) {
        return mockBillBean(BigDecimal.ONE);
    }
}
