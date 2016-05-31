package org.regkas.repository.core.repository;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.ReceiptElementRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.ProductGroupIncomeResult;
import org.regkas.repository.core.DateTimeHelperMock;
import org.regkas.service.api.bean.Period;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ReceiptElementRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptElementRepository receiptElementRepository;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Test
    @Transactional
    public void testBuilder() {
        receiptElementRepository.builder();
    }

    @Test
    @Transactional
    public void testLoadByPeriod() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals(3, receiptElementRepository.loadBy(Period.day(dateTimeHelper.currentTime()), cashBox).size());
        assertEquals(3, receiptElementRepository.loadBy(Period.month(dateTimeHelper.currentTime()), cashBox).size());
        assertEquals(0, receiptElementRepository.loadBy(Period.day(LocalDateTime.of(1970, 1, 1, 0, 0)), cashBox).size());
    }

    @Test
    @Transactional
    public void testIncomeByProductGroupFor() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        List<ProductGroupIncomeResult> productGroupIncomeResults = receiptElementRepository.incomeByProductGroupFor(Period.day(dateTimeHelper.currentTime()), cashBox);
        assertEquals("Snack", productGroupIncomeResults.get(0).getProductGroupName().get());
        assertEquals(new BigDecimal("5.00"), productGroupIncomeResults.get(2).getPricePaid().get());
    }
}
