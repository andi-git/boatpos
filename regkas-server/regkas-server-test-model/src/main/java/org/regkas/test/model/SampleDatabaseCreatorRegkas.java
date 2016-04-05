package org.regkas.test.model;

import com.google.common.collect.Sets;
import org.apache.commons.codec.digest.DigestUtils;
import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.test.SampleDatabaseCreator;
import org.regkas.model.*;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * Create a sample-database for tests.
 */
@SuppressWarnings("SqlNoDataSourceInspection")
@Dependent
public class SampleDatabaseCreatorRegkas implements SampleDatabaseCreator {

    @Resource
    private UserTransaction userTransaction;

    @Override
    @SuppressWarnings("OctalInteger")
    public void fillDatabase(EntityManager em) throws Exception {
        // clear cache and reset sequence for ids
        em.flush();
        em.clear();
        em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();

        AddressEntity address1 = new AddressEntity(null, null, "Street 1", "1220", "Vienna", "Austria", new HashSet<>());
        AddressEntity address2 = new AddressEntity(null, null, "Street 2", "1220", "Vienna", "Austria", new HashSet<>());
        UserEntity user1 = new UserEntity(null, null, true, 1, "", "", "Maria Musterfrau", DigestUtils.sha1Hex("abc123"), null);
        UserEntity user2 = new UserEntity(null, null, true, 2, "", "", "Max Mustermann", DigestUtils.sha1Hex("xyz789"), null);
        CashBoxEntity cashBox1 = new CashBoxEntity(null, null, true, 1, "", "", "RegKas1", "123", null);
        CashBoxEntity cashBox2 = new CashBoxEntity(null, null, true, 2, "", "", "RegKas2", "456", null);
        CompanyEntity company = new CompanyEntity(null, null, true, 1, "", "", "company", address1, "+431123456789", "office@company.com", "atu123", Sets.newHashSet(cashBox1, cashBox2), Sets.newHashSet(user1, user2), Sets.newHashSet());
        cashBox1.setCompany(company);
        cashBox2.setCompany(company);
        company.getCashBoxes().add(cashBox1);
        company.getCashBoxes().add(cashBox2);
        user1.setCompany(company);
        user2.setCompany(company);
        company.getUsers().add(user1);
        company.getUsers().add(user2);

        TaxSetEntity taxSet1 = new TaxSetEntity(null, null, true, 1, "", "", "Satz-Normal", 20, new HashSet<>());
        TaxSetEntity taxSet2 = new TaxSetEntity(null, null, true, 2, "", "", "Satz-Ermaessigt-1", 10, new HashSet<>());
        TaxSetEntity taxSet3 = new TaxSetEntity(null, null, true, 3, "", "", "Satz-Ermaessigt-2", 13, new HashSet<>());
        TaxSetEntity taxSet4 = new TaxSetEntity(null, null, true, 4, "", "", "Satz-Null", 0, new HashSet<>());
        TaxSetEntity taxSet5 = new TaxSetEntity(null, null, true, 5, "", "", "Satz-Besonders", 19, new HashSet<>());
        ProductGroupEntity productGroup1 = new ProductGroupEntity(null, null, true, 1, 'a', "", "", "Snack", taxSet2, company, new HashSet<>());
        ProductGroupEntity productGroup2 = new ProductGroupEntity(null, null, true, 2, 'b', "", "", "Eskimoeis", taxSet2, company, new HashSet<>());
        ProductGroupEntity productGroup3 = new ProductGroupEntity(null, null, true, 3, 'c', "", "", "Eismischgetränk", taxSet2, company, new HashSet<>());
        ProductGroupEntity productGroup4 = new ProductGroupEntity(null, null, true, 4, 'd', "", "", "Bier", taxSet1, company, new HashSet<>());
        ProductGroupEntity productGroup5 = new ProductGroupEntity(null, null, true, 5, 'e', "", "", "Wein", taxSet1, company, new HashSet<>());
        ProductGroupEntity productGroup6 = new ProductGroupEntity(null, null, true, 6, 'f', "", "", "alkoholfreies Getränk", taxSet1, company, new HashSet<>());
        ProductGroupEntity productGroup7 = new ProductGroupEntity(null, null, true, 7, 'g', "", "", "Kaffee", taxSet1, company, new HashSet<>());
        ProductEntity product1 = new ProductEntity(null, null, true, 1, 'A', "", "", "Snack", productGroup1, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity product2 = new ProductEntity(null, null, true, 2, 'B', "", "", "Cola", productGroup6, new HashSet<>(), new BigDecimal("2.50"), false);
        ProductEntity product3 = new ProductEntity(null, null, true, 3, 'C', "", "", "Cornetto", productGroup2, new HashSet<>(), new BigDecimal("2.00"), false);
        ProductEntity product4 = new ProductEntity(null, null, true, 4, 'D', "", "", "Wurstsemmel", productGroup1, new HashSet<>(), new BigDecimal("2.20"), false);
        productGroup1.getProducts().add(product1);
        productGroup1.getProducts().add(product4);
        productGroup6.getProducts().add(product2);
        productGroup2.getProducts().add(product3);

        ReceiptTypeEntity receiptType1 = new ReceiptTypeEntity(null, null, true, 1, "", "", "Start-Beleg");
        ReceiptTypeEntity receiptType2 = new ReceiptTypeEntity(null, null, true, 2, "", "", "Standard-Beleg");
        ReceiptTypeEntity receiptType3 = new ReceiptTypeEntity(null, null, true, 3, "", "", "Storno-Beleg");
        ReceiptTypeEntity receiptType4 = new ReceiptTypeEntity(null, null, true, 4, "", "", "Training-Beleg");
        ReceiptTypeEntity receiptType5 = new ReceiptTypeEntity(null, null, true, 5, "", "", "Null-Beleg");
        ReceiptEntity receipt1 = new ReceiptEntity(null, null, "2015-0000001", LocalDateTime.of(2015, 7, 1, 12, 00, 13), "12345", "sign", company, cashBox1, user1, receiptType2, PaymentMethod.Cash, TimeType.Current, new HashSet<>());
        ReceiptEntity receipt2 = new ReceiptEntity(null, null, "2015-0000002", LocalDateTime.of(2015, 7, 1, 12, 00, 13), "12345", "sign", company, cashBox1, user1, receiptType2, PaymentMethod.Cash, TimeType.Current, new HashSet<>());
        ReceiptElementEntity receiptElement11 = new ReceiptElementEntity(null, null, product2, receipt1, new BigDecimal("5.00"), 2);
        ReceiptElementEntity receiptElement12 = new ReceiptElementEntity(null, null, product3, receipt1, new BigDecimal("6.00"), 3);
        ReceiptElementEntity receiptElement21 = new ReceiptElementEntity(null, null, product4, receipt2, new BigDecimal("11.00"), 5);
        receipt1.getReceiptElements().add(receiptElement11);
        receipt1.getReceiptElements().add(receiptElement12);
        receipt2.getReceiptElements().add(receiptElement21);

        em.persist(company);
        em.persist(cashBox1);
        em.persist(cashBox2);
        em.persist(user1);
        em.persist(user2);
        em.persist(address1);
        em.persist(address2);
        em.persist(taxSet1);
        em.persist(taxSet2);
        em.persist(taxSet3);
        em.persist(taxSet4);
        em.persist(taxSet5);
        em.persist(product1);
        em.persist(productGroup1);
        em.persist(productGroup2);
        em.persist(productGroup3);
        em.persist(productGroup4);
        em.persist(productGroup5);
        em.persist(productGroup6);
        em.persist(productGroup7);
        em.persist(receiptType1);
        em.persist(receiptType2);
        em.persist(receiptType3);
        em.persist(receiptType4);
        em.persist(receiptType5);
        em.persist(receipt1);
        em.persist(receipt2);
        em.persist(receiptElement11);
        em.persist(receiptElement12);
        em.persist(receiptElement21);

        // company Eppel
        AddressEntity addressEppel = new AddressEntity(null, null, "Wagramerstraße 48a", "1220", "Wien", "Österreich", new HashSet<>());
        UserEntity userEppel = new UserEntity(null, null, true, 1, "", "", "Eppel", DigestUtils.sha1Hex("test123"), null);
        CashBoxEntity cashBoxEppelBootsvermietung = new CashBoxEntity(null, null, true, 1, "", "", "RegKasEppelBV", "", null);
        CashBoxEntity cashBoxEppelBuffet = new CashBoxEntity(null, null, true, 1, "", "", "RegKasEppelBuffet", "", null);
        CompanyEntity companyEppel = new CompanyEntity(null, null, true, 1, "", "", "EPPEL BOOTE", addressEppel, "+4312633530", "office@eppel-boote.at", "ATU63304105", Sets.newHashSet(cashBoxEppelBootsvermietung, cashBoxEppelBuffet), Sets.newHashSet(userEppel), Sets.newHashSet());
        cashBoxEppelBootsvermietung.setCompany(companyEppel);
        cashBoxEppelBuffet.setCompany(companyEppel);
        companyEppel.getCashBoxes().add(cashBoxEppelBootsvermietung);
        companyEppel.getCashBoxes().add(cashBoxEppelBuffet);
        userEppel.setCompany(companyEppel);
        companyEppel.getUsers().add(userEppel);
        // product-groups and generic products Eppel Bootsvermietung
        ProductGroupEntity productGroupEppelBVEBoot = new ProductGroupEntity(null, null, true, 1, 'a', "http://www.eppel-boote.at/boatpos/images/boat/eboot_2.jpg", "http://www.eppel-boote.at/boatpos/images/boat/eboot_2.jpg", "Elektroboot", taxSet1, companyEppel, new HashSet<>());
        ProductGroupEntity productGroupEppelBVTretboot = new ProductGroupEntity(null, null, true, 1, 'b', "http://www.eppel-boote.at/boatpos/images/boat/tretboot_2.jpg", "http://www.eppel-boote.at/boatpos/images/boat/tretboot_2.jpg", "Treetboot", taxSet1, companyEppel, new HashSet<>());
        ProductGroupEntity productGroupEppelBVRuderboot = new ProductGroupEntity(null, null, true, 1, 'c', "http://www.eppel-boote.at/boatpos/images/boat/ruderboot.jpg", "http://www.eppel-boote.at/boatpos/images/boat/ruderboot.jpg", "Liegeboot", taxSet1, companyEppel, new HashSet<>());
        ProductEntity productEppelBVGenericEBoot = new ProductEntity(null, null, true, 1, '#', "http://www.eppel-boote.at/boatpos/images/boat/eboot_2.jpg", "http://www.eppel-boote.at/boatpos/images/boat/eboot_2.jpg", "PG: Elektroboot", productGroupEppelBVEBoot, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBVGenericTretboot = new ProductEntity(null, null, true, 1, '#', "http://www.eppel-boote.at/boatpos/images/boat/tretboot_2.jpg", "http://www.eppel-boote.at/boatpos/images/boat/tretboot_2.jpg", "PG: Tretboot", productGroupEppelBVTretboot, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBVGenericRuderboot = new ProductEntity(null, null, true, 1, '#', "http://www.eppel-boote.at/boatpos/images/boat/ruderboot.jpg", "http://www.eppel-boote.at/boatpos/images/boat/ruderboot.jpg", "PG: Ruderboot", productGroupEppelBVRuderboot, new HashSet<>(), new BigDecimal("0.00"), true);
        // products Eppel Bootsvermietung
        ProductEntity productEppelBVE = new ProductEntity(null, null, true, 1, 'A', "http://www.eppel-boote.at/boatpos/images/boat/eboot_2.jpg", "http://www.eppel-boote.at/boatpos/images/boat/eboot_2.jpg", "E-Boot", productGroupEppelBVEBoot, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBVT2 = new ProductEntity(null, null, true, 1, 'B', "http://www.eppel-boote.at/boatpos/images/boat/tretboot_2.jpg", "http://www.eppel-boote.at/boatpos/images/boat/tretboot_2.jpg", "Tretboot klein", productGroupEppelBVTretboot, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBVT4 = new ProductEntity(null, null, true, 1, 'C', "http://www.eppel-boote.at/boatpos/images/boat/tretboot_4.jpg", "http://www.eppel-boote.at/boatpos/images/boat/tretboot_4.jpg", "Tretboot groß", productGroupEppelBVTretboot, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBVTR = new ProductEntity(null, null, true, 1, 'D', "http://www.eppel-boote.at/boatpos/images/boat/tretboot_rutsche.jpg", "http://www.eppel-boote.at/boatpos/images/boat/tretboot_rutsche.jpg", "Tretboot Rutsche", productGroupEppelBVTretboot, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBVP = new ProductEntity(null, null, true, 1, 'E', "http://www.eppel-boote.at/boatpos/images/boat/ruderboot.jpg", "http://www.eppel-boote.at/boatpos/images/boat/ruderboot.jpg", "Liegeboot", productGroupEppelBVRuderboot, new HashSet<>(), new BigDecimal("0.00"), false);
        // combine product-groups and products Eppel Bootsvermietung
        productGroupEppelBVEBoot.getProducts().add(productEppelBVGenericEBoot);
        productGroupEppelBVEBoot.getProducts().add(productEppelBVE);
        productGroupEppelBVTretboot.getProducts().add(productEppelBVGenericTretboot);
        productGroupEppelBVTretboot.getProducts().add(productEppelBVT2);
        productGroupEppelBVTretboot.getProducts().add(productEppelBVT4);
        productGroupEppelBVTretboot.getProducts().add(productEppelBVTR);
        productGroupEppelBVRuderboot.getProducts().add(productEppelBVGenericRuderboot);
        productGroupEppelBVRuderboot.getProducts().add(productEppelBVP);
        // product-groups Eppel Buffet
        ProductGroupEntity productGroupEppelBuffetSnack = new ProductGroupEntity(null, null, true, 1, 'a', "http://www.eppel-boote.at/regkas/images/buffet/snack.jpg", "http://www.eppel-boote.at/regkas/images/buffet/snack.jpg", "Snack", taxSet2, companyEppel, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetEskimoeis = new ProductGroupEntity(null, null, true, 2, 'b', "http://www.eppel-boote.at/regkas/images/buffet/eis.jpg", "http://www.eppel-boote.at/regkas/images/buffet/eis.jpg", "Eskimoeis", taxSet2, companyEppel, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetEismischgetraenk = new ProductGroupEntity(null, null, true, 3, 'c', "http://www.eppel-boote.at/regkas/images/buffet/eisgetraenk.jpg", "http://www.eppel-boote.at/regkas/images/buffet/eisgetraenk.jpg", "Eismischgetränk", taxSet2, companyEppel, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetBier = new ProductGroupEntity(null, null, true, 4, 'd', "http://www.eppel-boote.at/regkas/images/buffet/bier.jpg", "http://www.eppel-boote.at/regkas/images/buffet/bier.jpg", "Bier", taxSet1, companyEppel, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetWein = new ProductGroupEntity(null, null, true, 5, 'e', "http://www.eppel-boote.at/regkas/images/buffet/wein.jpg", "http://www.eppel-boote.at/regkas/images/buffet/wein.jpg", "Wein", taxSet1, companyEppel, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetAlkfrei = new ProductGroupEntity(null, null, true, 6, 'f', "http://www.eppel-boote.at/regkas/images/buffet/alkfrei.jpg", "http://www.eppel-boote.at/regkas/images/buffet/alkfrei.jpg", "alkoholfreies Getränk", taxSet1, companyEppel, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetKaffee = new ProductGroupEntity(null, null, true, 7, 'g', "http://www.eppel-boote.at/regkas/images/buffet/kaffee.jpg", "http://www.eppel-boote.at/regkas/images/buffet/kaffee.jpg", "Kaffee", taxSet1, companyEppel, new HashSet<>());
        // generic products Eppel Buffet
        ProductEntity productEppelBuffetGenericSnack = new ProductEntity(null, null, true, 1, '#', "http://www.eppel-boote.at/regkas/images/buffet/snack.jpg", "http://www.eppel-boote.at/regkas/images/buffet/snack.jpg", "PG: Snack", productGroupEppelBuffetSnack, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericEskimoeis = new ProductEntity(null, null, true, 1, '#', "http://www.eppel-boote.at/regkas/images/buffet/eis.jpg", "http://www.eppel-boote.at/regkas/images/buffet/eis.jpg", "PG: Eis", productGroupEppelBuffetEskimoeis, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericEismischgetraenk = new ProductEntity(null, null, true, 1, '#', "http://www.eppel-boote.at/regkas/images/buffet/eismischgetraenk.jpg", "http://www.eppel-boote.at/regkas/images/buffet/eismischgetraenk.jpg", "PG: Eismischgetränk", productGroupEppelBuffetEismischgetraenk, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericBier = new ProductEntity(null, null, true, 1, '#', "http://www.eppel-boote.at/regkas/images/buffet/bier.jpg", "http://www.eppel-boote.at/regkas/images/buffet/bier.jpg", "PG: Bier", productGroupEppelBuffetBier, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericWein = new ProductEntity(null, null, true, 1, '#', "http://www.eppel-boote.at/regkas/images/buffet/wein.jpg", "http://www.eppel-boote.at/regkas/images/buffet/wein.jpg", "PG: Wein", productGroupEppelBuffetWein, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericAlkfrei = new ProductEntity(null, null, true, 1, '#', "http://www.eppel-boote.at/regkas/images/buffet/alkfrei.jpg", "http://www.eppel-boote.at/regkas/images/buffet/alkfrei.jpg", "PG: Alkoholfrei", productGroupEppelBuffetAlkfrei, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericKaffee = new ProductEntity(null, null, true, 1, '#', "http://www.eppel-boote.at/regkas/images/buffet/kaffee.jpg", "http://www.eppel-boote.at/regkas/images/buffet/kaffee.jpg", "PG: Kaffee", productGroupEppelBuffetKaffee, new HashSet<>(), new BigDecimal("0.00"), true);
        // combine product-groups and products Eppel Bootsvermietung
        productGroupEppelBuffetSnack.getProducts().add(productEppelBuffetGenericSnack);
        productGroupEppelBuffetEskimoeis.getProducts().add(productEppelBuffetGenericEskimoeis);
        productGroupEppelBuffetEismischgetraenk.getProducts().add(productEppelBuffetGenericEismischgetraenk);
        productGroupEppelBuffetBier.getProducts().add(productEppelBuffetGenericBier);
        productGroupEppelBuffetWein.getProducts().add(productEppelBuffetGenericWein);
        productGroupEppelBuffetAlkfrei.getProducts().add(productEppelBuffetGenericAlkfrei);
        productGroupEppelBuffetKaffee.getProducts().add(productEppelBuffetGenericKaffee);

        // persist entities Bootsvermietung EPPEL
        em.persist(addressEppel);
        em.persist(userEppel);
        em.persist(cashBoxEppelBootsvermietung);
        em.persist(cashBoxEppelBuffet);
        em.persist(companyEppel);
        em.persist(productGroupEppelBVEBoot);
        em.persist(productGroupEppelBVTretboot);
        em.persist(productGroupEppelBVRuderboot);
        em.persist(productEppelBVGenericEBoot);
        em.persist(productEppelBVGenericTretboot);
        em.persist(productEppelBVGenericRuderboot);
        em.persist(productEppelBVE);
        em.persist(productEppelBVT2);
        em.persist(productEppelBVT4);
        em.persist(productEppelBVTR);
        em.persist(productEppelBVP);
        em.persist(productGroupEppelBuffetSnack);
        em.persist(productGroupEppelBuffetEskimoeis);
        em.persist(productGroupEppelBuffetEismischgetraenk);
        em.persist(productGroupEppelBuffetBier);
        em.persist(productGroupEppelBuffetWein);
        em.persist(productGroupEppelBuffetAlkfrei);
        em.persist(productGroupEppelBuffetKaffee);
        em.persist(productEppelBuffetGenericSnack);
        em.persist(productEppelBuffetGenericEskimoeis);
        em.persist(productEppelBuffetGenericEismischgetraenk);
        em.persist(productEppelBuffetGenericBier);
        em.persist(productEppelBuffetGenericWein);
        em.persist(productEppelBuffetGenericAlkfrei);
        em.persist(productEppelBuffetGenericKaffee);

        em.flush();
    }


    @Override
    public void clearDatabase(EntityManager em) throws Exception {
        if (Status.STATUS_ACTIVE == userTransaction.getStatus()) {
            // clear all tables
            em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
            em.createNativeQuery("DELETE FROM address").executeUpdate();
            em.createNativeQuery("DELETE FROM address_company").executeUpdate();
            em.createNativeQuery("DELETE FROM cashbox").executeUpdate();
            em.createNativeQuery("DELETE FROM company").executeUpdate();
            em.createNativeQuery("DELETE FROM company_cashbox").executeUpdate();
            em.createNativeQuery("DELETE FROM company_productgroup").executeUpdate();
            em.createNativeQuery("DELETE FROM company_user").executeUpdate();
            em.createNativeQuery("DELETE FROM product").executeUpdate();
            em.createNativeQuery("DELETE FROM product_receiptelement").executeUpdate();
            em.createNativeQuery("DELETE FROM productgroup").executeUpdate();
            em.createNativeQuery("DELETE FROM productgroup_product").executeUpdate();
            em.createNativeQuery("DELETE FROM receiptelement").executeUpdate();
            em.createNativeQuery("DELETE FROM receipt").executeUpdate();
            em.createNativeQuery("DELETE FROM receipttype").executeUpdate();
            em.createNativeQuery("DELETE FROM receipt_receiptelement").executeUpdate();
            em.createNativeQuery("DELETE FROM taxset").executeUpdate();
            em.createNativeQuery("DELETE FROM taxset_productgroup").executeUpdate();
            em.createNativeQuery("DELETE FROM user").executeUpdate();
            em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
            // reset sequence for ids and clear cache
            em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();
            em.flush();
            em.clear();
        }
    }
}
