package org.boatpos.dao.core;

//@ArquillianSuiteDeployment
//public class Deployments {
//
//    private static final String FOLDER_PERSISTENCE_XML_SOURCE = "src/test/resources";
//
//    private static final String FOLDER_PERSISTENCE_XML_TARGET = "META-INF";
//
//    private static final String FILE_PERSISTENCE_XML_SOURCE = "persistence-test.xml";
//
//    private static final String FILE_PERSISTENCE_XML_TARGET = "persistence.xml";
//
////    private static final String FOLDER_ORM_XML_SOURCE = "src/test/resources/META-INF/query";
////
////    private static final String FOLDER_ORM_XML_TARGET = "META-INF/query/";
//
//    @Deployment
//    public static WebArchive deploy() throws IOException {
//        return ShrinkWrap.create(WebArchive.class, "test.war")
//                .addAsLibraries(ArquillianHelper.getAllArquillianLibs())
//                .addAsWebInfResource("META-INF/beans.xml", "beans.xml")
//                .addAsWebInfResource("jboss-deployment-structure.xml")
//                .addAsResource(new File(FOLDER_PERSISTENCE_XML_SOURCE, FILE_PERSISTENCE_XML_SOURCE), FOLDER_PERSISTENCE_XML_TARGET + "/" + FILE_PERSISTENCE_XML_TARGET)
////                .addAsResource(new File(FOLDER_ORM_XML_SOURCE), FOLDER_ORM_XML_TARGET)
//                .addPackages(true, "org.boatpos.util")
//                .addPackages(true, "org.boatpos.test")
//                .addPackages(true, "org.boatpos.model")
//                .addPackages(true, "org.boatpos.dao.api")
//                .addPackages(true, "org.boatpos.dao.core");
//    }
//}
