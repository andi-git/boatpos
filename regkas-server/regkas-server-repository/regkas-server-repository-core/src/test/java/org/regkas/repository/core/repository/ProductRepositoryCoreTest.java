package org.regkas.repository.core.repository;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.ProductGroup;
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

    @Test
    @Transactional
    public void testLoadByName() {
        assertEquals("Cola", productRepository.loadBy(new Name("Cola")).get().getName().get());
    }

    @Test
    @Transactional
    public void testLoadByProductGroup() {
        Optional<ProductGroup> productGroup = productGroupRepository.loadBy(new Name("Snack"));
        assertEquals(2, productRepository.loadBy(productGroup.get()).size());
    }
}
