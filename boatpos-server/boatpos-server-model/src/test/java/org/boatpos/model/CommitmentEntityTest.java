package org.boatpos.model;

import org.boatpos.test.JavaBeanTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

@RunWith(Arquillian.class)
public class CommitmentEntityTest extends JavaBeanTest<CommitmentEntity> {

    @Test
    public void testConstructor() {
        new CommitmentEntity(null, 1, "commitment", false, new HashSet<>(), 1, true, 'a');
    }

    @Override
    protected Class<CommitmentEntity> getType() {
        return CommitmentEntity.class;
    }
}