package org.boatpos.domain.core.model;

import org.boatpos.domain.core.TestUtil;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class CommitmentCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private TestUtil.CommitmentUtil commitmentUtil;

    @Test
    @Transactional
    public void testPersist() {
        commitmentUtil.assertDatabaseCommitmentCount(6);
        commitmentUtil.createDummyCommitment().persist();
        commitmentUtil.assertDatabaseCommitmentCount(7);
    }

    @Test
    @Transactional
    public void testEntity() {
        assertEquals("Pass", new CommitmentCore(commitmentUtil.createDummyCommitment().asEntity()).getName().get());
    }

    @Test
    @Transactional
    public void testDto() {
        assertEquals("Pass", new CommitmentCore(commitmentUtil.createDummyCommitment().asDto()).getName().get());
    }
}
