package org.boatpos.domain.core.repository;

import org.boatpos.domain.api.repository.CommitmentRepository;
import org.boatpos.domain.api.values.Name;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CommitmentRepositoryCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private CommitmentRepository commitmentRepository;

    @Test
    @Transactional
    public void testLoadBy() throws Exception {
        assertTrue(commitmentRepository.loadBy(new Name("Ausweis")).get().needPaper().get());
    }
}