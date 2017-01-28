package org.regkas.service.core.receipt;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.model.*;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ReceiptTypeConverterTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptTypeConverter receiptTypeConverter;

    @Test
    @Transactional
    public void testConvertToReceiptType() throws Exception {
        assertEquals(ReceiptTypeJahrCore.class, receiptTypeConverter.convertToReceiptType(new Name("Jahres-Beleg")).getClass());
        assertEquals(ReceiptTypeMonatCore.class, receiptTypeConverter.convertToReceiptType(new Name("Monats-Beleg")).getClass());
        assertEquals(ReceiptTypeNullCore.class, receiptTypeConverter.convertToReceiptType(new Name("Null-Beleg")).getClass());
        assertEquals(ReceiptTypeStandardCore.class, receiptTypeConverter.convertToReceiptType(new Name("Standard-Beleg")).getClass());
        assertEquals(ReceiptTypeStartCore.class, receiptTypeConverter.convertToReceiptType(new Name("Start-Beleg")).getClass());
        assertEquals(ReceiptTypeStornoCore.class, receiptTypeConverter.convertToReceiptType(new Name("Storno-Beleg")).getClass());
        assertEquals(ReceiptTypeTrainingCore.class, receiptTypeConverter.convertToReceiptType(new Name("Training-Beleg")).getClass());
    }

    @Test(expected = NullPointerException.class)
    @Transactional
    public void testConvertToReceiptTypeNameNull() throws Exception {
        Name name = null;
        //noinspection ConstantConditions
        receiptTypeConverter.convertToReceiptType(name);
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testConvertToReceiptTypeNameWrong() throws Exception {
        receiptTypeConverter.convertToReceiptType(new Name("_unknown_"));
    }
}