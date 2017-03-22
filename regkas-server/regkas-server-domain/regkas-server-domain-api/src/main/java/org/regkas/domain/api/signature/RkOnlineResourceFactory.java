package org.regkas.domain.api.signature;

public interface RkOnlineResourceFactory {

    void setRkOnlineResourceSession(RkOnlineResourceSession rkOnlineResourceSession);

    RkOnlineResourceSession getRkOnlineResourceSession();

    RkOnlineResourceSession resetRkOnlineResourceSession();

    void setRkOnlineResourceSignature(RkOnlineResourceSignature rkOnlineResourceSignature);

    RkOnlineResourceSignature getRkOnlineResourceSignature();

    RkOnlineResourceSignature resetRkOnlineResourceSignature();

}
