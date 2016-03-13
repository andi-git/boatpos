package org.boatpos.common.repository.core.builder;

import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.repository.core.boat.*;
import org.boatpos.common.test.EntityManagerProvider;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class BuilderTest implements EntityManagerProvider {

    @PersistenceContext(unitName = "my-persistence")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Inject
    private BoatBuilder boatBuilder;

    @Test
    @Transactional
    public void testBuild() throws Exception {
        assertEquals("dummy",
                boatBuilder
                        .add(new DomainId(99L))
                        .add(new Version(5))
                        .add(Enabled.TRUE)
                        .add(new Priority(3))
                        .add(new KeyBinding('x'))
                        .add(new PictureUrl("pic"))
                        .add(new PictureUrlThumb("thumb"))
                        .add(new Name("dummy"))
                        .add(new Price("9.99"))
                        .build()
                        .getName().get());
        assertEquals("dummy", boatBuilder.from(BoatBean.createDummyWithoutId1()).getName().get());
        assertEquals("dummy", boatBuilder.from(BoatEntity.createDummyWithoutId1()).getName().get());
    }
}
