package org.boatpos.common.domain.core;

import org.boatpos.common.test.ArquillianHelper;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
@ArquillianSuiteDeployment
public class Deployments {

    private static final String FILE_PERSISTENCE_XML_SOURCE = "src/test/resources/persistence-test.xml";
    private static final String FILE_PERSISTENCE_XML_TARGET = "META-INF/persistence.xml";
    private static final String FOLDER_ORM_XML_SOURCE = "src/test/resources/query";
    private static final String FOLDER_ORM_XML_TARGET = "META-INF/query/";
    private static final String FILE_ARQUILLIAN_EXTENSION_SOURCE = "../../boatpos-common-test/src/main/resources/META-INF/services/org.jboss.arquillian.container.test.spi.RemoteLoadableExtension";
    private static final String FILE_ARQUILLIAN_EXTENSION_TARGET = "META-INF/services/org.jboss.arquillian.container.test.spi.RemoteLoadableExtension";
    private static final String FILE_DOZER_SOURCE = "src/test/resources/dozerBeanMapping.xml";
    private static final String FILE_DOZER_TARGET = "META-INF/dozerBeanMapping.xml";

    @Deployment
    public static WebArchive deploy() throws IOException {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsLibraries(ArquillianHelper.getAllArquillianLibs())
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml")
                .addAsWebInfResource("jboss-deployment-structure.xml")
                .addAsResource(new File(FILE_DOZER_SOURCE))
                .addAsResource(new File(FILE_PERSISTENCE_XML_SOURCE), FILE_PERSISTENCE_XML_TARGET)
                .addAsResource(new File(FOLDER_ORM_XML_SOURCE), FOLDER_ORM_XML_TARGET)
                .addAsResource(new File(FILE_ARQUILLIAN_EXTENSION_SOURCE), FILE_ARQUILLIAN_EXTENSION_TARGET)
                .addPackages(true, "org.boatpos.common.util")
                .addPackages(true, "org.boatpos.common.test")
                .addPackages(true, "org.boatpos.common.model")
                .addPackages(true, "org.boatpos.common.domain.api")
                .addPackages(true, "org.boatpos.common.domain.core")
                .addPackages(true, "org.boatpos.common.service.api");
    }
}
