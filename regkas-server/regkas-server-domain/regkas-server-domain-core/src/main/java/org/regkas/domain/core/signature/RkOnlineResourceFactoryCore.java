package org.regkas.domain.core.signature;

import org.regkas.domain.api.signature.RkOnlineResourceFactory;
import org.regkas.domain.api.signature.RkOnlineResourceSession;
import org.regkas.domain.api.signature.RkOnlineResourceSignature;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RkOnlineResourceFactoryCore implements RkOnlineResourceFactory {

    @Inject
    private RkOnlineResourceSession rkOnlineResourceSessionDefault;

    @Inject
    private RkOnlineResourceSignature rkOnlineResourceSignatureDefault;

    private RkOnlineResourceSession rkOnlineResourceSessionCurrent;

    private RkOnlineResourceSignature rkOnlineResourceSignatureCurrent;

    @PostConstruct
    public void init() {
        rkOnlineResourceSessionCurrent = rkOnlineResourceSessionDefault;
        rkOnlineResourceSignatureCurrent = rkOnlineResourceSignatureDefault;
    }

    @Override
    public void setRkOnlineResourceSession(RkOnlineResourceSession rkOnlineResourceSession) {
        this.rkOnlineResourceSessionCurrent = rkOnlineResourceSession;
    }

    @Override
    public RkOnlineResourceSession getRkOnlineResourceSession() {
        return rkOnlineResourceSessionCurrent;
    }

    @Override
    public RkOnlineResourceSession resetRkOnlineResourceSession() {
        setRkOnlineResourceSession(rkOnlineResourceSessionDefault);
        return getRkOnlineResourceSession();
    }

    @Override
    public void setRkOnlineResourceSignature(RkOnlineResourceSignature rkOnlineResourceSignature) {
        this.rkOnlineResourceSignatureCurrent = rkOnlineResourceSignature;
    }

    @Override
    public RkOnlineResourceSignature getRkOnlineResourceSignature() {
        return rkOnlineResourceSignatureCurrent;
    }

    @Override
    public RkOnlineResourceSignature resetRkOnlineResourceSignature() {
        setRkOnlineResourceSignature(rkOnlineResourceSignatureDefault);
        return getRkOnlineResourceSignature();
    }
}
