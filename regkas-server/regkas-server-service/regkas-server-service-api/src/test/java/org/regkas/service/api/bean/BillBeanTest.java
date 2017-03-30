package org.regkas.service.api.bean;

import com.google.common.collect.Lists;
import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class BillBeanTest extends JavaBeanTest<BillBean> {

    @Test
    public void testConstructor() {
        new BillBean(new CompanyBean(),
                "cashbox",
                "id",
                LocalDateTime.now(),
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                BigDecimal.ONE,
                Lists.newArrayList(),
                BigDecimal.ONE,
                "Standard-Beleg",
                "",
                false);
    }

    @Test
    public void testFromJwsComact() {
        String jwsCompact = "_R1-AT100_CASHBOX-DEMO-1_CASHBOX-DEMO-1-Receipt-ID-1_2016-03-11T03:57:08_1,10_2,20_3,30_4,40_5,50_4r1iIdZG_82424e8077297aa3_cg8hNU5ihto=_rKThZdYRoO4cyowsAK3KQjmgXg9cA3XmGVnR53EtYloroivDePObBTlGy6XhAOx2d4ZROEMcGI5crmBGLo0mBA==";
        BillBean billBean = BillBean.fromJwsCompact(jwsCompact);
        assertEquals("CASHBOX-DEMO-1", billBean.getCashBoxID());
        assertEquals("CASHBOX-DEMO-1-Receipt-ID-1", billBean.getReceiptIdentifier());
        assertEquals(LocalDateTime.of(2016, 3, 11, 3, 57, 8), billBean.getReceiptDateAndTime());
        assertEquals(new BigDecimal("1.10"), billBean.getSumTaxSetNormal());
        assertEquals(new BigDecimal("2.20"), billBean.getSumTaxSetErmaessigt1());
        assertEquals(new BigDecimal("3.30"), billBean.getSumTaxSetErmaessigt2());
        assertEquals(new BigDecimal("4.40"), billBean.getSumTaxSetNull());
        assertEquals(new BigDecimal("5.50"), billBean.getSumTaxSetBesonders());
        assertEquals("4r1iIdZG", billBean.getEncryptedTurnoverValue());
        assertEquals("82424e8077297aa3", billBean.getSignatureCertificateSerialNumber());
        assertEquals("cg8hNU5ihto=", billBean.getSignatureValuePreviousReceipt());
        assertEquals(jwsCompact, billBean.getJwsCompact());
    }
}