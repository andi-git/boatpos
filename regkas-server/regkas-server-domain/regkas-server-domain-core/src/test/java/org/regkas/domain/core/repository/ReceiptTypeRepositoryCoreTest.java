package org.regkas.domain.core.repository;

import org.boatpos.common.domain.api.values.Enabled;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.repository.ReceiptTypeRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class ReceiptTypeRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptTypeRepository receiptTypeRepository;

    @Test
    @Transactional
    public void testLoadByName() {
        assertEquals("Start-Beleg", receiptTypeRepository.loadBy(new Name("Start-Beleg")).get().getName().get());
    }

    @Test
    @Transactional
    public void testLoadAll() {
        assertEquals(10, receiptTypeRepository.loadAll().size());
    }

    @Test
    @Transactional
    public void testLoadAllEnabledDisable() {
        assertEquals(10, receiptTypeRepository.loadAll(Enabled.TRUE).size());
        assertEquals(0, receiptTypeRepository.loadAll(Enabled.FALSE).size());
    }
}
