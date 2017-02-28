package org.regkas.repository.core.signature;

import static org.junit.Assert.assertEquals;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.signature.CompactJWSRepresentation;
import org.regkas.repository.api.signature.RkOnlineResourceFactory;
import org.regkas.repository.api.signature.RkOnlineResourceSession;
import org.regkas.repository.api.signature.RkOnlineResourceSignature;
import org.regkas.repository.api.values.JWSPayload;

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

    @Alternative
    private static final class MockRkOnlineResourceSignature implements RkOnlineResourceSignature {

        @Override
        public CompactJWSRepresentation sign(JWSPayload jwsPayload) {
            return null;
        }
    }

    private static final class MockRkOnlineResourceSession implements RkOnlineResourceSession {

        @Override
        public void loginSession() {

        }
    }
}
