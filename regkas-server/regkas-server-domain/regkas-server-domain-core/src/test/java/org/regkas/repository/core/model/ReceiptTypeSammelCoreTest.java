package org.regkas.repository.core.model;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.repository.ReceiptTypeRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.turnovercounter.UpdateTurnoverCounterNothing;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class ReceiptTypeSammelCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptTypeRepository receiptTypeRepository;

    private ReceiptType receiptType;

    @Before
    public void before() {
        receiptType = receiptTypeRepository.loadBy(new Name("Sammel-Beleg")).get();
    }

    @Test
    @Transactional
    public void testGetSammelBelegFromDatabase() {
        assertEquals(ReceiptTypeSammelCore.class, receiptType.getClass());
        assertEquals("Sammel-Beleg", receiptType.getName().get());
    }

    @Test
    @Transactional
    public void getGetUpdateTurnoverCounter() {
        assertEquals(UpdateTurnoverCounterNothing.class, receiptType.getUpdateTurnoverCounter().getClass().getSuperclass());
    }
}
