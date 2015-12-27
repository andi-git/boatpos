package org.boatpos.repository.core.repository;

import com.google.common.collect.Sets;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.NonUniqueResultException;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Arquillian.class)
public class JPAHelperTest {

    @Inject
    private JPAHelper jpaHelper;

    @Test(expected = NonUniqueResultException.class)
    public void testGetSingleResult() throws Exception {
        assertFalse(jpaHelper.getSingleResult(new HashSet<>()).isPresent());
        assertEquals("test", jpaHelper.getSingleResult(Sets.newHashSet("test")).get());
        jpaHelper.getSingleResult(Sets.newHashSet("test", "abc"));
    }
}