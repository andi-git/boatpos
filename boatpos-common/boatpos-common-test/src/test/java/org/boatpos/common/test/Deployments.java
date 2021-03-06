package org.boatpos.common.test;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
@ArquillianSuiteDeployment
public class Deployments {

    private static final String FOLDER_PERSISTENCE_XML_SOURCE = "src/test/resources";
    private static final String FOLDER_PERSISTENCE_XML_TARGET = "META-INF";
    private static final String FILE_ARQUILLIAN_EXTENSION_SOURCE = "src/main/resources/META-INF/services/org.jboss.arquillian.container.test.spi.RemoteLoadableExtension";
    private static final String FILE_ARQUILLIAN_EXTENSION_TARGET = "META-INF/services/org.jboss.arquillian.container.test.spi.RemoteLoadableExtension";

    @Deployment
    public static WebArchive deploy() throws IOException {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsLibraries(ArquillianHelper.getAllArquillianLibs())
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml")
                .addAsResource(new File(FILE_ARQUILLIAN_EXTENSION_SOURCE), FILE_ARQUILLIAN_EXTENSION_TARGET)
                .addPackages(true, "org.boatpos.common.test");
    }
}
