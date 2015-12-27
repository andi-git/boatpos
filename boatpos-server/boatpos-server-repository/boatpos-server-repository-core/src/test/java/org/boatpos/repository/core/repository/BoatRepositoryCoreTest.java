package org.boatpos.repository.core.repository;

import org.boatpos.repository.api.repository.BoatRepository;
import org.boatpos.repository.api.values.DomainId;
import org.boatpos.repository.api.values.Enabled;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.api.values.ShortName;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class BoatRepositoryCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private BoatRepository boatRepository;

    @Test
    @Transactional
    public void testLoadByDomainId() {
        assertEquals("E-Boot", boatRepository.loadBy(new DomainId(1L)).get().getName().get());
    }

    @Test
    @Transactional
    public void testLoadByName() {
        assertEquals("E", boatRepository.loadBy(new Name("E-Boot")).get().getShortName().get());
    }

    @Test
    @Transactional
    public void testLoadByShortName() {
        assertEquals("E-Boot", boatRepository.loadBy(new ShortName("E")).get().getName().get());
    }

    @Test
    @Transactional
    public void testLoadAll() {
        assertEquals(6, boatRepository.loadAll().size());
        assertEquals(5, boatRepository.loadAll(Enabled.TRUE).size());
        assertEquals(1, boatRepository.loadAll(Enabled.FALSE).size());
    }
}
