package org.regkas.repository.core.model;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.repository.TaxSetRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.TotalPrice;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class TaxSetNormalCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private TaxSetRepository taxSetRepository;

    private TaxSet taxSet;

    @Before
    public void before() {
        taxSet = taxSetRepository.loadBy(new Name("Satz-Normal")).get();
    }

    @Test
    @Transactional
    public void testGetter() {
        assertEquals("Satz-Normal", taxSet.getName().get());
        assertEquals(20, taxSet.getTaxPercent().get().intValue());
    }

    @Test
    @Transactional
    public void testGetTaxOf() {
        assertEquals(new BigDecimal("2.00"), taxSet.getTaxOf(new TotalPrice("12.00")).get());
        assertEquals(new BigDecimal("0.00"), taxSetRepository.loadBy(new Name("Satz-Null")).get().getTaxOf(new TotalPrice("12.00")).get());
    }

    @Test
    @Transactional
    public void testGetPriceWithoutTaxOf() {
        assertEquals(new BigDecimal("10.00"), taxSet.getPriceWithoutTaxOf(new TotalPrice("12.00")).get());
        assertEquals(new BigDecimal("12.00"), taxSetRepository.loadBy(new Name("Satz-Null")).get().getPriceWithoutTaxOf(new TotalPrice("12.00")).get());
    }

}