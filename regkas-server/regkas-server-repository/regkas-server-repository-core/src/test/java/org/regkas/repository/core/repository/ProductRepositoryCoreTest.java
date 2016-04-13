package org.regkas.repository.core.repository;

import org.boatpos.common.repository.api.values.Enabled;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.ProductGroupRepository;
import org.regkas.repository.api.repository.ProductRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ProductRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductGroupRepository productGroupRepository;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Test
    @Transactional
    public void testLoadByNameAndCompany() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals("Cola", productRepository.loadBy(new Name("Cola"), cashBox).get().getName().get());
    }

    @Test
    @Transactional
    public void testLoadGenericByProductGroup() {
        Optional<ProductGroup> productGroup = productGroupRepository.loadBy(new Name("Snack"), cashBoxRepository.loadBy(new Name("RegKas1")).get());
        assertEquals("Snack", productRepository.loadGenericBy(productGroup.get()).get().getName().get());
    }

    @Test
    @Transactional
    public void testLoadByProductGroup() {
        Optional<ProductGroup> productGroup = productGroupRepository.loadBy(new Name("Snack"), cashBoxRepository.loadBy(new Name("RegKas1")).get());
        assertEquals(2, productRepository.loadBy(productGroup.get()).size());
    }

    @Test
    @Transactional
    public void testLoadByCompany() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals(4, productRepository.loadBy(cashBox).size());
        assertEquals(4, productRepository.loadBy(cashBox, Enabled.TRUE).size());
        assertEquals(0, productRepository.loadBy(cashBox, Enabled.FALSE).size());
    }
}
