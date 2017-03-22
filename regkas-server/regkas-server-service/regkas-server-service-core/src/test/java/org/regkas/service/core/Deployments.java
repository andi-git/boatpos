package org.regkas.service.core;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.boatpos.common.test.ArquillianHelper;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

@SuppressWarnings({"unused", "ConstantConditions"})
@ArquillianSuiteDeployment
public class Deployments {

    private static final String FILE_PERSISTENCE_XML_SOURCE = "../../regkas-server-domain/regkas-server-domain-core/src/test/resources/persistence-test.xml";
    private static final String FILE_PERSISTENCE_XML_TARGET = "META-INF/persistence.xml";
    private static final String FOLDER_ORM_XML_SOURCE = "../../regkas-server-domain/regkas-server-domain-core/src/main/resources/META-INF/query";
    private static final String FOLDER_ORM_XML_TARGET = "META-INF/query/";
    private static final String FILE_ARQUILLIAN_EXTENSION_SOURCE = "../../../boatpos-common/boatpos-common-test/src/main/resources/META-INF/services/org.jboss.arquillian.container.test.spi.RemoteLoadableExtension";
    private static final String FILE_ARQUILLIAN_EXTENSION_TARGET = "META-INF/services/org.jboss.arquillian.container.test.spi.RemoteLoadableExtension";
    private static final String FILE_DOZER_SOURCE = "../../regkas-server-domain/regkas-server-domain-core/src/main/resources/dozerBeanMapping.xml";

    @Deployment
    public static WebArchive deploy() throws Exception {
        File regkassenVerificationDepformatSourceFile = new File(Thread.currentThread().getContextClassLoader().getResource("testdata/regkassen-verification-depformat-1.0.0.jar").getFile());
        File regkassenVerificationDepformatDestFile = new File(System.getProperty("java.io.tmpdir") + "/regkassen-verification-depformat-1.0.0.jar");
        File regkassenVerificationReceiptsSourceFile = new File(Thread.currentThread().getContextClassLoader().getResource("testdata/regkassen-verification-receipts-1.0.0.jar").getFile());
        File regkassenVerificationReceiptsDestFile = new File(System.getProperty("java.io.tmpdir") + "/regkassen-verification-receipts-1.0.0.jar");
        File cryptographicMaterialContainerSourceFile = new File(Thread.currentThread().getContextClassLoader().getResource("testdata/cryptographicMaterialContainer.json").getFile());
        File cryptographicMaterialContainerDestFile = new File(System.getProperty("java.io.tmpdir") + "/cryptographicMaterialContainer.json");
        File folderLibSource = new File(Thread.currentThread().getContextClassLoader().getResource("testdata/lib").getFile());
        File folderLibDest = new File(System.getProperty("java.io.tmpdir") + "/lib");
        FileUtils.copyDirectory(folderLibSource, folderLibDest);
        FileUtils.copyFile(regkassenVerificationDepformatSourceFile, regkassenVerificationDepformatDestFile);
        FileUtils.copyFile(regkassenVerificationReceiptsSourceFile, regkassenVerificationReceiptsDestFile);
        FileUtils.copyFile(cryptographicMaterialContainerSourceFile, cryptographicMaterialContainerDestFile);
        WebArchive webArchive = ShrinkWrap
            .create(WebArchive.class, "test.war")
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
            .addPackages(true, "org.boatpos.common.service.api")
            .addPackages(true, "org.boatpos.common.service.core")
            .addPackages(true, "org.regkas.model")
            .addPackages(true, "org.regkas.test.model")
            .addPackages(true, "org.regkas.domain.api")
            .addPackages(true, "org.regkas.domain.core")
            .addPackages(true, "org.regkas.service.api")
            .addPackages(true, "org.regkas.service.core");
        addTestdata(webArchive, "testdata/szenario01/TESTSUITE_TEST_SZENARIO_1.json");
        addTestdata(webArchive, "testdata/szenario01/qr-code-rep.json");
        addTestdata(webArchive, "testdata/szenario02/TESTSUITE_TEST_SZENARIO_2.json");
        addTestdata(webArchive, "testdata/szenario02/qr-code-rep.json");
        addTestdata(webArchive, "testdata/szenario03/TESTSUITE_TEST_SZENARIO_3.json");
        addTestdata(webArchive, "testdata/szenario03/qr-code-rep.json");
        addTestdata(webArchive, "testdata/szenario04/TESTSUITE_TEST_SZENARIO_4.json");
        addTestdata(webArchive, "testdata/szenario04/qr-code-rep.json");
        addTestdata(webArchive, "testdata/szenario05/TESTSUITE_TEST_SZENARIO_5.json");
        addTestdata(webArchive, "testdata/szenario05/qr-code-rep.json");
        addTestdata(webArchive, "testdata/szenario06/TESTSUITE_TEST_SZENARIO_6.json");
        addTestdata(webArchive, "testdata/szenario06/qr-code-rep.json");
        addTestdata(webArchive, "testdata/szenario07/TESTSUITE_TEST_SZENARIO_7.json");
        addTestdata(webArchive, "testdata/szenario07/qr-code-rep.json");
        addTestdata(webArchive, "testdata/szenario08/TESTSUITE_TEST_SZENARIO_8.json");
        addTestdata(webArchive, "testdata/szenario08/qr-code-rep.json");
        return webArchive;
    }

    private static WebArchive addTestdata(WebArchive webArchive, String name) throws Exception {
        webArchive.addAsResource(new File(Thread.currentThread().getContextClassLoader().getResource(name).toURI()), name);
        return webArchive;
    }
}
