package org.regkas.domain.core.signature;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.signature.RkOnlineResourceFactory;

@RunWith(Arquillian.class)
public class RkOnlineResourceFactoryCoreTest {

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Test
    public void testRkOnlineResourceSignature() {
        assertEquals(RkOnlineResourceSignatureCore.class, rkOnlineResourceFactory.getRkOnlineResourceSignature().getClass().getSuperclass());
        rkOnlineResourceFactory.setRkOnlineResourceSignature(new MockRkOnlineResourceSignature());
        assertEquals(MockRkOnlineResourceSignature.class, rkOnlineResourceFactory.getRkOnlineResourceSignature().getClass());
        assertEquals(RkOnlineResourceSignatureCore.class, rkOnlineResourceFactory.resetRkOnlineResourceSignature().getClass().getSuperclass());
        assertEquals(RkOnlineResourceSignatureCore.class, rkOnlineResourceFactory.getRkOnlineResourceSignature().getClass().getSuperclass());
    }

    @Test
    public void testRkOnlineResourceSession() {
        assertEquals(RkOnlineResourceSessionCore.class, rkOnlineResourceFactory.getRkOnlineResourceSession().getClass().getSuperclass());
        rkOnlineResourceFactory.setRkOnlineResourceSession(new MockRkOnlineResourceSession());
        assertEquals(MockRkOnlineResourceSession.class, rkOnlineResourceFactory.getRkOnlineResourceSession().getClass());
        assertEquals(RkOnlineResourceSessionCore.class, rkOnlineResourceFactory.resetRkOnlineResourceSession().getClass().getSuperclass());
        assertEquals(RkOnlineResourceSessionCore.class, rkOnlineResourceFactory.getRkOnlineResourceSession().getClass().getSuperclass());
    }

}
