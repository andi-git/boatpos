package org.regkas.repository.core.repository;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.core.DateTimeHelperMock;
import org.regkas.service.api.bean.Period;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ReceiptRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Test
    @Transactional
    public void testLoadByPeriod() {
        assertEquals(2, receiptRepository.loadBy(Period.day(dateTimeHelper.currentTime())).size());
        assertEquals(2, receiptRepository.loadBy(Period.month(dateTimeHelper.currentTime())).size());
        assertEquals(0, receiptRepository.loadBy(Period.day(LocalDateTime.of(1970, 1, 1, 0, 0))).size());
    }
}
