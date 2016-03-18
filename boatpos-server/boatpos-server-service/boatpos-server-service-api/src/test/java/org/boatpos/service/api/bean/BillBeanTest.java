package org.boatpos.service.api.bean;

import com.google.common.collect.Lists;
import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BillBeanTest extends JavaBeanTest<BillBean>  {

    @Test
    public void testConstructorAndSomeGetterSetter() {
        BillBean bill = new BillBean(new CompanyBean("name", "street", "zip", "country", "phone", "mail", "atu"),
                "cashbox",
                "id",
                LocalDateTime.now(),
                Lists.newArrayList(new TaxSetBean.TaxSetNormalBean("name", BigDecimal.ONE, 1)),
                Lists.newArrayList(new TaxSetBean.TaxSetErmaessigt1Bean("name", BigDecimal.ONE, 1)),
                Lists.newArrayList(new TaxSetBean.TaxSetErmaessigt2Bean("name", BigDecimal.ONE, 1)),
                Lists.newArrayList(new TaxSetBean.TaxSetBesonderesBean("name", BigDecimal.ONE, 1)),
                Lists.newArrayList(new TaxSetBean.TaxSetNullBean("name", BigDecimal.ONE, 1)));
        bill.getTaxSetNormal();
        bill.getTaxSetErmaessigt1();
        bill.getTaxSetErmaessigt2();
        bill.getTaxSetBesonderesBean();
        bill.getTaxSetNullBean();
        bill.setTaxSetNormal(Lists.newArrayList());
        bill.setTaxSetErmaessigt1(Lists.newArrayList());
        bill.setTaxSetErmaessigt2(Lists.newArrayList());
        bill.setTaxSetBesonderesBean(Lists.newArrayList());
        bill.setTaxSetNullBean(Lists.newArrayList());
    }
}