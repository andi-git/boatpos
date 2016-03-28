package org.regkas.repository.core.repository;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.repository.AddressRepository;
import org.regkas.repository.api.repository.ReceiptElementRepository;
import org.regkas.repository.core.builder.AddressBuilderCore;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ReceiptElementRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptElementRepository receiptElementRepository;

    @Test
    @Transactional
    public void testSomething() {
        receiptElementRepository.builder();
    }
}
