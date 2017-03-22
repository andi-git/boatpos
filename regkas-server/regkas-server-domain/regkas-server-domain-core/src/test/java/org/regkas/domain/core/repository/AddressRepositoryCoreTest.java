package org.regkas.domain.core.repository;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.repository.AddressRepository;
import org.regkas.domain.core.builder.AddressBuilderCore;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class AddressRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private AddressRepository addressRepository;

    @Test
    @Transactional
    public void testBuilder() {
        assertEquals(AddressBuilderCore.class, addressRepository.builder().getClass());
    }
}
