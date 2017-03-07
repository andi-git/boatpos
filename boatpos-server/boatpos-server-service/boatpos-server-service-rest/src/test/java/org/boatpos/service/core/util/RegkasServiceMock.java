package org.boatpos.service.core.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Specializes;

import org.boatpos.service.api.bean.PaymentBean;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.CompanyBean;

@Dependent
@Specializes
public class RegkasServiceMock extends RegkasService {

    @Override
    public BillBean sale(PaymentBean paymentBean) throws Exception {
        return new BillBean(
            new CompanyBean(),
            "cashboxId",
            "receipt-id",
            LocalDateTime.now(),
            paymentBean.getValue(),
            BigDecimal.ZERO,
            BigDecimal.ZERO,
            BigDecimal.ZERO,
            BigDecimal.ZERO,
            new ArrayList<>(),
            paymentBean.getValue(),
            "Standard-Beleg",
            "");
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
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
