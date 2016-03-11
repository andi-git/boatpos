package org.boatpos.common.test;

import org.jboss.arquillian.container.test.spi.RemoteLoadableExtension;

public class FillDatabaseExtension implements RemoteLoadableExtension {

    @Override
    public void register(ExtensionBuilder builder) {
        builder.observer(FillDatabaseExecuter.class);
    }
}