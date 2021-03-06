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
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Create a sample-database for tests.
 */
@SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
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
        CashBoxEntity cashBox1 = new CashBoxEntity(null, null, true, 1, "", "", "RegKas1", "123", null, "192.168.0.11", "ONRcz49yLDIo2FgwNhe9Q5fSiZFEies97uRMzeAAPkI=", 1300L, "AT0", "u123456789", "123456789");
        CashBoxEntity cashBox2 = new CashBoxEntity(null, null, true, 2, "", "", "RegKas2", "456", null, "192.168.0.21", "TyoYsvuuaDLWB40VZiLwUxEsELJHzfBkuLO6cP8Oz/Q=", 2500L, "AT0", "u123456789", "123456789");
        CashBoxEntity cashBox3 = new CashBoxEntity(null, null, true, 3, "", "", "RegKas3", "123", null, "192.168.0.21", "ONRcz49yLDIo2FgwNhe9Q5fSiZFEies97uRMzeAAPkI=", 1300L, "AT0", "u123456789", "123456789");
        CashBoxEntity cashBoxStart = new CashBoxEntity(null, null, true, 3, "", "", "RegKasStart", "111", null, "192.168.0.21", "TyoYsvuuaDLWB40VZiLwUxEsELJHzfBkuLO6cP8Oz/Q=", 0L, "AT0", "u123456789", "123456789");
        CashBoxEntity demoCashBox817 = new CashBoxEntity(null, null, true, 3, "", "", "DEMO-CASH-BOX817", "111", null, "192.168.0.21", "RCsRmHn5tkLQrRpiZq2ucwPpwvHJLiMgLvwrwEImddI=", 8733L, "AT0", "u123456789", "123456789");
        CompanyEntity company = new CompanyEntity(null, null, true, 1, "", "", "company", address1, "+431123456789", "office@company.com", "atu123", Sets.newHashSet(cashBox1, cashBox2), Sets.newHashSet(user1, user2), Sets.newHashSet());
        cashBox1.setCompany(company);
        cashBox2.setCompany(company);
        cashBox3.setCompany(company);
        cashBoxStart.setCompany(company);
        demoCashBox817.setCompany(company);
        company.getCashBoxes().add(cashBox1);
        company.getCashBoxes().add(cashBox2);
        company.getCashBoxes().add(cashBox3);
        user1.setCompany(company);
        user2.setCompany(company);
        company.getUsers().add(user1);
        company.getUsers().add(user2);

        TaxSetEntity taxSet1 = new TaxSetNormalEntity(null, null, true, 1, "", "", "Satz-Normal", 20, new HashSet<>());
        TaxSetEntity taxSet2 = new TaxSetErmaessigt1Entity(null, null, true, 2, "", "", "Satz-Ermaessigt-1", 10, new HashSet<>());
        TaxSetEntity taxSet3 = new TaxSetErmaessigt2Entity(null, null, true, 3, "", "", "Satz-Ermaessigt-2", 13, new HashSet<>());
        TaxSetEntity taxSet4 = new TaxSetNullEntity(null, null, true, 4, "", "", "Satz-Null", 0, new HashSet<>());
        TaxSetEntity taxSet5 = new TaxSetBesondersEntity(null, null, true, 5, "", "", "Satz-Besonders", 19, new HashSet<>());
        TaxSetEntity taxSet6 = new TaxSetCoronaEntity(null, null, true, 6, "", "", "Satz-Corona", 5, new HashSet<>());
        ProductGroupEntity productGroup1 = new ProductGroupEntity(null, null, true, 1, 'a', "", "", "Snack", taxSet2, cashBox1, new HashSet<>());
        ProductGroupEntity productGroup2 = new ProductGroupEntity(null, null, true, 2, 'b', "", "", "Eskimoeis", taxSet2, cashBox1, new HashSet<>());
        ProductGroupEntity productGroup3 = new ProductGroupEntity(null, null, true, 3, 'c', "", "", "Eismischgetränk", taxSet2, cashBox1, new HashSet<>());
        ProductGroupEntity productGroup4 = new ProductGroupEntity(null, null, true, 4, 'd', "", "", "Bier", taxSet1, cashBox1, new HashSet<>());
        ProductGroupEntity productGroup5 = new ProductGroupEntity(null, null, true, 5, 'e', "", "", "Wein", taxSet1, cashBox1, new HashSet<>());
        ProductGroupEntity productGroup6 = new ProductGroupEntity(null, null, true, 6, 'f', "", "", "alkoholfreies Getränk", taxSet1, cashBox1, new HashSet<>());
        ProductGroupEntity productGroup7 = new ProductGroupEntity(null, null, true, 7, 'g', "", "", "Kaffee", taxSet1, cashBox1, new HashSet<>());
        ProductGroupEntity productGroup8 = new ProductGroupEntity(null, null, true, 8, 'g', "", "", "Corona", taxSet6, cashBox3, new HashSet<>());
        ProductGroupEntity productGroup9 = new ProductGroupEntity(null, null, true, 8, 'g', "", "", "Normal", taxSet1, cashBox3, new HashSet<>());
        ProductEntity product1 = new ProductEntity(null, null, true, 1, 'A', "", "", "Snack", productGroup1, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity product2 = new ProductEntity(null, null, true, 2, 'B', "", "", "Cola", productGroup6, new HashSet<>(), new BigDecimal("2.50"), false);
        ProductEntity product3 = new ProductEntity(null, null, true, 3, 'C', "", "", "Cornetto", productGroup2, new HashSet<>(), new BigDecimal("2.00"), false);
        ProductEntity product4 = new ProductEntity(null, null, true, 4, 'D', "", "", "Wurstsemmel", productGroup1, new HashSet<>(), new BigDecimal("2.20"), false);
        ProductEntity product5 = new ProductEntity(null, null, true, 4, 'E', "", "", "Corona", productGroup8, new HashSet<>(), new BigDecimal("1.00"), false);
        ProductEntity product6 = new ProductEntity(null, null, true, 4, 'E', "", "", "Normal", productGroup9, new HashSet<>(), new BigDecimal("1.00"), false);
        productGroup1.getProducts().add(product1);
        productGroup1.getProducts().add(product4);
        productGroup6.getProducts().add(product2);
        productGroup2.getProducts().add(product3);
        productGroup8.getProducts().add(product5);
        productGroup9.getProducts().add(product6);

        ReceiptTypeEntity receiptTypeStart = new ReceiptTypeStartEntity(null, null, true, 1, "", "", "Start-Beleg", true);
        ReceiptTypeEntity receiptTypeStandard = new ReceiptTypeStandardEntity(null, null, true, 2, "", "", "Standard-Beleg", false);
        ReceiptTypeEntity receiptTypeStorno = new ReceiptTypeStornoEntity(null, null, true, 3, "", "", "Storno-Beleg", false);
        ReceiptTypeEntity receiptTypeTraining = new ReceiptTypeTrainingEntity(null, null, true, 4, "", "", "Training-Beleg", false);
        ReceiptTypeEntity receiptTypeNull = new ReceiptTypeNullEntity(null, null, true, 5, "", "", "Null-Beleg", false);
        ReceiptTypeEntity receiptTypeMonat = new ReceiptTypeMonatEntity(null, null, true, 6, "", "", "Monats-Beleg", false);
        ReceiptTypeEntity receiptTypeJahr = new ReceiptTypeJahrEntity(null, null, true, 7, "", "", "Jahres-Beleg", true);
        ReceiptTypeEntity receiptTypeSammel = new ReceiptTypeSammelEntity(null, null, true, 8, "", "", "Sammel-Beleg", true);
        ReceiptTypeEntity receiptTypeSchluss = new ReceiptTypeSchlussEntity(null, null, true, 9, "", "", "Schluss-Beleg", false);
        ReceiptTypeEntity receiptTypeTag = new ReceiptTypeTagEntity(null, null, true, 10, "", "", "Tages-Beleg", false);
        ReceiptEntity receipt0 = new ReceiptEntity(null, null, "2015-0000000", LocalDateTime.of(2015, 7, 1, 12, 00, 00), "12345", "sign", company, cashBox1, user1, receiptTypeStart, PaymentMethod.CASH, new ArrayList<>(), "", new BigDecimal("0.00"), "R1-AT0", "jws123", "xxx.jws123.sss", true);
        ReceiptEntity receipt1 = new ReceiptEntity(null, null, "2015-0000001", LocalDateTime.of(2015, 7, 1, 12, 00, 13), "12345", "sign", company, cashBox1, user1, receiptTypeStandard, PaymentMethod.CASH, new ArrayList<>(), "{\"Kassen-ID\":\"RegKas1\",\"Belegnummer\":\"2015-0000001\",\"Beleg-Datum-Uhrzeit\":\"2015-07-01T12:00:13\",\"Betrag-Satz-Normal\":5.00,\"Betrag-Satz-Ermaessigt-1\":6.00,\"Betrag-Satz-Ermaessigt-2\":0.00,\"Betrag-Satz-Null\":0.00,\"Betrag-Satz-Besonders\":0.00,\"Belegelemente\":[{\"Produkt\":\"Cola\",\"Steuersatz\":20,\"Anzahl\":2,\"Netto\":4.17,\"Brutto\":5.00,\"Steuer\":0.83},{\"Produkt\":\"Cornetto\",\"Steuersatz\":10,\"Anzahl\":3,\"Netto\":5.45,\"Brutto\":6.00,\"Steuer\":0.55}],\"Gesamtbetrag\":11.00,\"Beleg-Art\":\"Standard-Beleg\"}", new BigDecimal("11.00"), "R1-AT0", "jws123", "xxx.jws123.sss", true);
        ReceiptEntity receipt2 = new ReceiptEntity(null, null, "2015-0000002", LocalDateTime.of(2015, 7, 1, 12, 00, 13), "12345", "sign", company, cashBox1, user1, receiptTypeStandard, PaymentMethod.CASH, new ArrayList<>(), "{\"Kassen-ID\":\"RegKas1\",\"Belegnummer\":\"2015-0000002\",\"Beleg-Datum-Uhrzeit\":\"2015-07-01T12:00:13\",\"Betrag-Satz-Normal\":0.00,\"Betrag-Satz-Ermaessigt-1\":11.00,\"Betrag-Satz-Ermaessigt-2\":0.00,\"Betrag-Satz-Null\":0.00,\"Betrag-Satz-Besonders\":0.00,\"Belegelemente\":[{\"Produkt\":\"Wurstsemmel\",\"Steuersatz\":10,\"Anzahl\":5,\"Netto\":10.00,\"Brutto\":11.00,\"Steuer\":1.00}],\"Gesamtbetrag\":11.00,\"Beleg-Art\":\"Standard-Beleg\"}", new BigDecimal("11.00"), "R1-AT0", "jws456", "xxx.jws456.sss", true);
        ReceiptEntity receipt30 = new ReceiptEntity(null, null, "2015-0000000", LocalDateTime.of(2015, 7, 1, 12, 00, 00), "12345", "sign", company, cashBox3, user1, receiptTypeStart, PaymentMethod.CASH, new ArrayList<>(), "", new BigDecimal("0.00"), "R1-AT0", "jws123", "xxx.jws123.sss", true);
        ReceiptEntity receipt31 = new ReceiptEntity(null, null, "2015-0000001", LocalDateTime.of(2015, 7, 1, 12, 00, 20), "12345", "sign", company, cashBox3, user1, receiptTypeStandard, PaymentMethod.CASH, new ArrayList<>(), "{\"Kassen-ID\":\"RegKas3\",\"Belegnummer\":\"2015-0000001\",\"Beleg-Datum-Uhrzeit\":\"2015-07-01T12:00:20\",\"Betrag-Satz-Normal\":6.00,\"Betrag-Satz-Ermaessigt-1\":0.00,\"Betrag-Satz-Ermaessigt-2\":0.00,\"Betrag-Satz-Null\":4.00,\"Betrag-Satz-Besonders\":0.00,\"Belegelemente\":[{\"Produkt\":\"Normal\",\"Steuersatz\":20,\"Anzahl\":1,\"Netto\":5.00,\"Brutto\":6.00,\"Steuer\":1.00},{\"Produkt\":\"Corona\",\"Steuersatz\":5,\"Anzahl\":1,\"Netto\":3,81,\"Brutto\":4.00,\"Steuer\":0.19}],\"Gesamtbetrag\":10.00,\"Beleg-Art\":\"Standard-Beleg\"}", new BigDecimal("10.00"), "R1-AT0", "jws456", "xxx.jws456.sss", true);
        ReceiptEntity receipt32 = new ReceiptEntity(null, null, "2015-0000002", LocalDateTime.of(2015, 7, 1, 12, 00, 21), "12345", "sign", company, cashBox3, user1, receiptTypeStandard, PaymentMethod.CARD, new ArrayList<>(), "{\"Kassen-ID\":\"RegKas3\",\"Belegnummer\":\"2015-0000002\",\"Beleg-Datum-Uhrzeit\":\"2015-07-01T12:00:21\",\"Betrag-Satz-Normal\":6.00,\"Betrag-Satz-Ermaessigt-1\":0.00,\"Betrag-Satz-Ermaessigt-2\":0.00,\"Betrag-Satz-Null\":4.00,\"Betrag-Satz-Besonders\":0.00,\"Belegelemente\":[{\"Produkt\":\"Normal\",\"Steuersatz\":20,\"Anzahl\":1,\"Netto\":5.00,\"Brutto\":6.00,\"Steuer\":1.00},{\"Produkt\":\"Corona\",\"Steuersatz\":5,\"Anzahl\":1,\"Netto\":3,81,\"Brutto\":4.00,\"Steuer\":0.19}],\"Gesamtbetrag\":10.00,\"Beleg-Art\":\"Standard-Beleg\"}", new BigDecimal("10.00"), "R1-AT0", "jws456", "xxx.jws456.sss", true);
        ReceiptElementEntity receiptElement11 = new ReceiptElementEntity(null, null, product2, receipt1, new BigDecimal("5.00"), 2);
        ReceiptElementEntity receiptElement12 = new ReceiptElementEntity(null, null, product3, receipt1, new BigDecimal("6.00"), 3);
        ReceiptElementEntity receiptElement21 = new ReceiptElementEntity(null, null, product4, receipt2, new BigDecimal("11.00"), 5);
        ReceiptElementEntity receiptElement31 = new ReceiptElementEntity(null, null, product6, receipt31, new BigDecimal("6.00"), 1);
        ReceiptElementEntity receiptElement32 = new ReceiptElementEntity(null, null, product5, receipt31, new BigDecimal("4.00"), 1);
        ReceiptElementEntity receiptElement33 = new ReceiptElementEntity(null, null, product6, receipt32, new BigDecimal("6.00"), 1);
        ReceiptElementEntity receiptElement34 = new ReceiptElementEntity(null, null, product5, receipt32, new BigDecimal("4.00"), 1);
        receipt1.getReceiptElements().add(receiptElement11);
        receipt1.getReceiptElements().add(receiptElement12);
        receipt2.getReceiptElements().add(receiptElement21);
        receipt31.getReceiptElements().add(receiptElement31);
        receipt31.getReceiptElements().add(receiptElement32);
        receipt32.getReceiptElements().add(receiptElement33);
        receipt32.getReceiptElements().add(receiptElement34);

        em.persist(company);
        em.persist(cashBox1);
        em.persist(cashBox2);
        em.persist(cashBox3);
        em.persist(cashBoxStart);
        em.persist(demoCashBox817);
        em.persist(user1);
        em.persist(user2);
        em.persist(address1);
        em.persist(address2);
        em.persist(taxSet1);
        em.persist(taxSet2);
        em.persist(taxSet3);
        em.persist(taxSet4);
        em.persist(taxSet5);
        em.persist(taxSet6);
        em.persist(product1);
        em.persist(product2);
        em.persist(product3);
        em.persist(product4);
        em.persist(product5);
        em.persist(product6);
        em.persist(productGroup1);
        em.persist(productGroup2);
        em.persist(productGroup3);
        em.persist(productGroup4);
        em.persist(productGroup5);
        em.persist(productGroup6);
        em.persist(productGroup7);
        em.persist(productGroup8);
        em.persist(productGroup9);
        em.persist(receiptTypeStart);
        em.persist(receiptTypeStandard);
        em.persist(receiptTypeStorno);
        em.persist(receiptTypeTraining);
        em.persist(receiptTypeNull);
        em.persist(receiptTypeMonat);
        em.persist(receiptTypeJahr);
        em.persist(receiptTypeSammel);
        em.persist(receiptTypeSchluss);
        em.persist(receiptTypeTag);
        em.persist(receipt0);
        em.persist(receipt1);
        em.persist(receipt2);
        em.persist(receipt30);
        em.persist(receipt31);
        em.persist(receipt32);
        em.persist(receiptElement11);
        em.persist(receiptElement12);
        em.persist(receiptElement21);
        em.persist(receiptElement31);
        em.persist(receiptElement32);
        em.persist(receiptElement33);
        em.persist(receiptElement34);

        /*
         * Test Suite 01
         */

        UserEntity userDemo01 = new UserEntity(null, null, true, 2, "", "", "User Demo01", DigestUtils.sha1Hex("xyz789"), null);
        CashBoxEntity cashBoxDemo01 = new CashBoxEntity(null, null, true, 4, "", "", "CASHBOX-DEMO-1", "37C76F3D", null, "192.168.0.21", "WQRtiiya3hYh/Uz44Bv3x8ETl1nrH6nCdErn69g5/lU=", 0L, "AT100", "u123456789", "123456789");
        CompanyEntity companyDemo01 = new CompanyEntity(null, null, true, 2, "", "", "company demo 01", address1, "+431123456789", "office@company.com", "U:ATU12345678", Sets.newHashSet(cashBoxDemo01), Sets.newHashSet(userDemo01), Sets.newHashSet());
        cashBoxDemo01.setCompany(companyDemo01);
        companyDemo01.getCashBoxes().add(cashBoxDemo01);
        userDemo01.setCompany(companyDemo01);
        companyDemo01.getUsers().add(userDemo01);
        em.persist(companyDemo01);
        em.persist(cashBoxDemo01);
        em.persist(userDemo01);
        ProductGroupEntity productGroupStandard = new ProductGroupEntity(null, null, true, 8, 'h', "", "", "Standard", taxSet1, cashBoxDemo01, new HashSet<>());
        ProductGroupEntity productGroupErmaessigt1 = new ProductGroupEntity(null, null, true, 8, 'h', "", "", "Ermaessigt1", taxSet2, cashBoxDemo01, new HashSet<>());
        ProductGroupEntity productGroupErmaessigt2 = new ProductGroupEntity(null, null, true, 8, 'h', "", "", "Ermaessigt2", taxSet3, cashBoxDemo01, new HashSet<>());
        ProductGroupEntity productGroupNull = new ProductGroupEntity(null, null, true, 9, 'i', "", "", "Null", taxSet4, cashBoxDemo01, new HashSet<>());
        ProductGroupEntity productGroupBesonders = new ProductGroupEntity(null, null, true, 10, 'j', "", "", "Besonders", taxSet5, cashBoxDemo01, new HashSet<>());
        ProductGroupEntity productGroupCorona = new ProductGroupEntity(null, null, true, 11, 'k', "", "", "Corona", taxSet6, cashBoxDemo01, new HashSet<>());
        ProductEntity productStandard = new ProductEntity(null, null, true, 4, 'D', "", "", "Standard", productGroupStandard, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productErmaessigt1 = new ProductEntity(null, null, true, 4, 'D', "", "", "Ermaessigt1", productGroupErmaessigt1, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productErmaessigt2 = new ProductEntity(null, null, true, 4, 'D', "", "", "Ermaessigt2", productGroupErmaessigt2, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productNull = new ProductEntity(null, null, true, 4, 'D', "", "", "Null", productGroupNull, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productBesonders = new ProductEntity(null, null, true, 4, 'D', "", "", "Besonders", productGroupBesonders, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productCorona = new ProductEntity(null, null, true, 5, 'E', "", "", "Corona", productGroupCorona, new HashSet<>(), new BigDecimal("0.00"), false);
        productGroupStandard.getProducts().add(productStandard);
        productGroupErmaessigt1.getProducts().add(productErmaessigt1);
        productGroupErmaessigt2.getProducts().add(productErmaessigt2);
        productGroupNull.getProducts().add(productNull);
        productGroupBesonders.getProducts().add(productBesonders);
        productGroupCorona.getProducts().add(productCorona);

        em.persist(productGroupStandard);
        em.persist(productGroupErmaessigt1);
        em.persist(productGroupErmaessigt2);
        em.persist(productGroupNull);
        em.persist(productGroupBesonders);
        em.persist(productGroupCorona);
        em.persist(productStandard);
        em.persist(productErmaessigt1);
        em.persist(productErmaessigt2);
        em.persist(productNull);
        em.persist(productBesonders);
        em.persist(productCorona);

        /*
         * company eppel
         */

        AddressEntity addressEppel = new AddressEntity(null, null, "Wagramerstraße 48a", "1220", "Wien", "Österreich", new HashSet<>());
        UserEntity userEppel = new UserEntity(null, null, true, 1, "", "", "Eppel", DigestUtils.sha1Hex("test123"), null);
        CashBoxEntity cashBoxEppelBootsvermietung = new CashBoxEntity(null, null, true, 1, "", "", "RegKasEppelBV", "", null, "192.168.0.11", "ONRcz49yLDIo2FgwNhe9Q5fSiZFEies97uRMzeAAPkI=", 1300L, "AT0", "u123456789", "123456789");
        CashBoxEntity cashBoxEppelBuffet = new CashBoxEntity(null, null, true, 1, "", "", "RegKasEppelBuffet", "", null, "192.168.0.12", "TyoYsvuuaDLWB40VZiLwUxEsELJHzfBkuLO6cP8Oz/Q=", 2500L, "AT0", "u123456789", "123456789");
        CompanyEntity companyEppel = new CompanyEntity(null, null, true, 1, "", "", "EPPEL BOOTE", addressEppel, "+4312633530", "office@eppel-boote.at", "ATU63304105", Sets.newHashSet(cashBoxEppelBootsvermietung, cashBoxEppelBuffet), Sets.newHashSet(userEppel), Sets.newHashSet());
        cashBoxEppelBootsvermietung.setCompany(companyEppel);
        cashBoxEppelBuffet.setCompany(companyEppel);
        companyEppel.getCashBoxes().add(cashBoxEppelBootsvermietung);
        companyEppel.getCashBoxes().add(cashBoxEppelBuffet);
        userEppel.setCompany(companyEppel);
        companyEppel.getUsers().add(userEppel);
        // product-groups and generic products Eppel Bootsvermietung
        ProductGroupEntity productGroupEppelBVEBoot = new ProductGroupEntity(null, null, true, 1, 'a', "http://www.eppel-boote.at/boatpos/images/boat/eboot_2.jpg", "http://www.eppel-boote.at/boatpos/images/boat/eboot_2.jpg", "Elektroboot", taxSet1, cashBoxEppelBootsvermietung, new HashSet<>());
        ProductGroupEntity productGroupEppelBVTretboot = new ProductGroupEntity(null, null, true, 1, 'b', "http://www.eppel-boote.at/boatpos/images/boat/tretboot_2.jpg", "http://www.eppel-boote.at/boatpos/images/boat/tretboot_2.jpg", "Treetboot", taxSet1, cashBoxEppelBootsvermietung, new HashSet<>());
        ProductGroupEntity productGroupEppelBVRuderboot = new ProductGroupEntity(null, null, true, 1, 'c', "http://www.eppel-boote.at/boatpos/images/boat/ruderboot.jpg", "http://www.eppel-boote.at/boatpos/images/boat/ruderboot.jpg", "Liegeboot", taxSet1, cashBoxEppelBootsvermietung, new HashSet<>());
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
        ProductGroupEntity productGroupEppelBuffetEskimoeis = new ProductGroupEntity(null, null, true, 1, 'a', urlImgProdBuffet("eis"), urlImgProdBuffet("eis"), "Eskimoeis", taxSet2, cashBoxEppelBuffet, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetAlkfrei = new ProductGroupEntity(null, null, true, 2, 'b', urlImgProdBuffet("alkfrei"), urlImgProdBuffet("alkfrei"), "alkoholfreies Getränk", taxSet1, cashBoxEppelBuffet, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetKaffee = new ProductGroupEntity(null, null, true, 3, 'c', urlImgProdBuffet("kaffee"), urlImgProdBuffet("kaffee"), "Kaffee", taxSet1, cashBoxEppelBuffet, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetBier = new ProductGroupEntity(null, null, true, 4, 'd', urlImgProdBuffet("bier"), urlImgProdBuffet("bier"), "Bier", taxSet1, cashBoxEppelBuffet, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetWein = new ProductGroupEntity(null, null, true, 5, 'e', urlImgProdBuffet("wein"), urlImgProdBuffet("wein"), "Wein", taxSet1, cashBoxEppelBuffet, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetWarmeSpeisen = new ProductGroupEntity(null, null, true, 6, 'f', urlImgProdBuffet("speisen"), urlImgProdBuffet("speisen"), "Warme Speisen", taxSet2, cashBoxEppelBuffet, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetBelegtesGebaeck = new ProductGroupEntity(null, null, true, 7, 'g', urlImgProdBuffet("gebaeck"), urlImgProdBuffet("gebaeck"), "Belegtes Gebäck", taxSet2, cashBoxEppelBuffet, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetKuchen = new ProductGroupEntity(null, null, true, 8, 'h', urlImgProdBuffet("kuchen"), urlImgProdBuffet("kuchen"), "Kuchen", taxSet2, cashBoxEppelBuffet, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetSnack = new ProductGroupEntity(null, null, true, 9, 'i', urlImgProdBuffet("snack"), urlImgProdBuffet("snack"), "Snack", taxSet2, cashBoxEppelBuffet, new HashSet<>());
        ProductGroupEntity productGroupEppelBuffetEismischgetraenk = new ProductGroupEntity(null, null, true, 10, 'j', urlImgProdBuffet("eisgetraenk"), urlImgProdBuffet("eisgetraenk"), "Eismischgetränk", taxSet2, cashBoxEppelBuffet, new HashSet<>());
        // generic products Eppel Buffet
        ProductEntity productEppelBuffetGenericEskimoeis = new ProductEntity(null, null, true, 1, '#', urlImgProdBuffet("eis"), urlImgProdBuffet("eis"), "PG: Eis", productGroupEppelBuffetEskimoeis, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericAlkfrei = new ProductEntity(null, null, true, 2, '#', urlImgProdBuffet("alkfrei"), urlImgProdBuffet("alkfrei"), "PG: Alkoholfrei", productGroupEppelBuffetAlkfrei, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericKaffee = new ProductEntity(null, null, true, 3, '#', urlImgProdBuffet("kaffee"), urlImgProdBuffet("kaffee"), "PG: Kaffee", productGroupEppelBuffetKaffee, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericBier = new ProductEntity(null, null, true, 4, '#', urlImgProdBuffet("bier"), urlImgProdBuffet("bier"), "PG: Bier", productGroupEppelBuffetBier, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericWein = new ProductEntity(null, null, true, 5, '#', urlImgProdBuffet("wein"), urlImgProdBuffet("wein"), "PG: Wein", productGroupEppelBuffetWein, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericWarmeSpeisen = new ProductEntity(null, null, true, 6, '#', urlImgProdBuffet("speisen"), urlImgProdBuffet("speisen"), "PG: Warme Speisen", productGroupEppelBuffetWarmeSpeisen, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericBelegtesGebaeck = new ProductEntity(null, null, true, 7, '#', urlImgProdBuffet("gebaeck"), urlImgProdBuffet("gebaeck"), "PG: Belegtes Gebäck", productGroupEppelBuffetBelegtesGebaeck, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericKuchen = new ProductEntity(null, null, true, 8, '#', urlImgProdBuffet("kuchen"), urlImgProdBuffet("kuchen"), "PG: Kuchen", productGroupEppelBuffetKuchen, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericSnack = new ProductEntity(null, null, true, 9, '#', urlImgProdBuffet("snack"), urlImgProdBuffet("snack"), "PG: Snack", productGroupEppelBuffetSnack, new HashSet<>(), new BigDecimal("0.00"), true);
        ProductEntity productEppelBuffetGenericEismischgetraenk = new ProductEntity(null, null, true, 10, '#', urlImgProdBuffet("eismischgetraenk"), urlImgProdBuffet("eismischgetraenk"), "PG: Eismischgetränk", productGroupEppelBuffetEismischgetraenk, new HashSet<>(), new BigDecimal("0.00"), true);
        // products Eppel Buffet
        ProductEntity productEppelBuffetMineral = new ProductEntity(null, null, true, 1, 'A', urlImgProdBuffet("mineral"), urlImgProdBuffet("mineral"), "Mineral", productGroupEppelBuffetAlkfrei, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBuffetBierGrosz = new ProductEntity(null, null, true, 2, 'B', urlImgProdBuffet("bierg"), urlImgProdBuffet("bierg"), "Bier groß", productGroupEppelBuffetBier, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBuffetBierKlein = new ProductEntity(null, null, true, 3, 'C', urlImgProdBuffet("bierk"), urlImgProdBuffet("bierk"), "Bier klein", productGroupEppelBuffetBier, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBuffetCola = new ProductEntity(null, null, true, 4, 'D', urlImgProdBuffet("cola"), urlImgProdBuffet("cola"), "Cola", productGroupEppelBuffetAlkfrei, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBuffetWeinGespr = new ProductEntity(null, null, true, 5, 'E', urlImgProdBuffet("weingespr"), urlImgProdBuffet("weingespr"), "Wein gespritzt", productGroupEppelBuffetWein, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBuffetStifterl = new ProductEntity(null, null, true, 6, 'F', urlImgProdBuffet("stifterl"), urlImgProdBuffet("stifterl"), "Stifterl", productGroupEppelBuffetWein, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBuffetEistee = new ProductEntity(null, null, true, 7, 'G', urlImgProdBuffet("eistee"), urlImgProdBuffet("eistee"), "Eistee", productGroupEppelBuffetAlkfrei, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBuffetCapri = new ProductEntity(null, null, true, 8, 'H', urlImgProdBuffet("capri"), urlImgProdBuffet("capri"), "Capri", productGroupEppelBuffetAlkfrei, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBuffetRedBull = new ProductEntity(null, null, true, 9, 'I', urlImgProdBuffet("redbull"), urlImgProdBuffet("redbull"), "RedBull", productGroupEppelBuffetAlkfrei, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBuffetCafemio = new ProductEntity(null, null, true, 10, 'J', urlImgProdBuffet("cafemio"), urlImgProdBuffet("cafemio"), "Cafemio", productGroupEppelBuffetKaffee, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBuffetMelange = new ProductEntity(null, null, true, 11, 'K', urlImgProdBuffet("melange"), urlImgProdBuffet("melange"), "Melange", productGroupEppelBuffetKaffee, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBuffetGroszerBrauner = new ProductEntity(null, null, true, 12, 'L', urlImgProdBuffet("groszerbr"), urlImgProdBuffet("groszerbr"), "Großer Brauner", productGroupEppelBuffetKaffee, new HashSet<>(), new BigDecimal("0.00"), false);
        ProductEntity productEppelBuffetKleinerBrauner = new ProductEntity(null, null, true, 13, 'M', urlImgProdBuffet("kleinerbr"), urlImgProdBuffet("kleinerbr"), "Kleiner Brauner", productGroupEppelBuffetKaffee, new HashSet<>(), new BigDecimal("0.00"), false);

        // combine product-groups and products Eppel Bootsvermietung
        productGroupEppelBuffetEskimoeis.getProducts().add(productEppelBuffetGenericEskimoeis);
        productGroupEppelBuffetAlkfrei.getProducts().add(productEppelBuffetGenericAlkfrei);
        productGroupEppelBuffetAlkfrei.getProducts().add(productEppelBuffetMineral);
        productGroupEppelBuffetAlkfrei.getProducts().add(productEppelBuffetCola);
        productGroupEppelBuffetAlkfrei.getProducts().add(productEppelBuffetEistee);
        productGroupEppelBuffetAlkfrei.getProducts().add(productEppelBuffetCapri);
        productGroupEppelBuffetAlkfrei.getProducts().add(productEppelBuffetRedBull);
        productGroupEppelBuffetKaffee.getProducts().add(productEppelBuffetGenericKaffee);
        productGroupEppelBuffetKaffee.getProducts().add(productEppelBuffetCafemio);
        productGroupEppelBuffetKaffee.getProducts().add(productEppelBuffetMelange);
        productGroupEppelBuffetKaffee.getProducts().add(productEppelBuffetGroszerBrauner);
        productGroupEppelBuffetKaffee.getProducts().add(productEppelBuffetKleinerBrauner);
        productGroupEppelBuffetBier.getProducts().add(productEppelBuffetGenericBier);
        productGroupEppelBuffetBier.getProducts().add(productEppelBuffetBierGrosz);
        productGroupEppelBuffetBier.getProducts().add(productEppelBuffetBierKlein);
        productGroupEppelBuffetWein.getProducts().add(productEppelBuffetGenericWein);
        productGroupEppelBuffetWein.getProducts().add(productEppelBuffetWeinGespr);
        productGroupEppelBuffetWein.getProducts().add(productEppelBuffetStifterl);
        productGroupEppelBuffetWarmeSpeisen.getProducts().add(productEppelBuffetGenericWarmeSpeisen);
        productGroupEppelBuffetBelegtesGebaeck.getProducts().add(productEppelBuffetGenericBelegtesGebaeck);
        productGroupEppelBuffetKuchen.getProducts().add(productEppelBuffetGenericKuchen);
        productGroupEppelBuffetSnack.getProducts().add(productEppelBuffetGenericSnack);
        productGroupEppelBuffetEismischgetraenk.getProducts().add(productEppelBuffetGenericEismischgetraenk);

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

        em.persist(productGroupEppelBuffetEskimoeis);
        em.persist(productGroupEppelBuffetAlkfrei);
        em.persist(productGroupEppelBuffetKaffee);
        em.persist(productGroupEppelBuffetBier);
        em.persist(productGroupEppelBuffetWein);
        em.persist(productGroupEppelBuffetWarmeSpeisen);
        em.persist(productGroupEppelBuffetBelegtesGebaeck);
        em.persist(productGroupEppelBuffetKuchen);
        em.persist(productGroupEppelBuffetSnack);
        em.persist(productGroupEppelBuffetEismischgetraenk);

        em.persist(productEppelBuffetGenericEskimoeis);
        em.persist(productEppelBuffetGenericAlkfrei);
        em.persist(productEppelBuffetGenericKaffee);
        em.persist(productEppelBuffetGenericBier);
        em.persist(productEppelBuffetGenericWein);
        em.persist(productEppelBuffetGenericWarmeSpeisen);
        em.persist(productEppelBuffetGenericBelegtesGebaeck);
        em.persist(productEppelBuffetGenericKuchen);
        em.persist(productEppelBuffetGenericSnack);
        em.persist(productEppelBuffetGenericEismischgetraenk);
        em.persist(productEppelBuffetMineral);
        em.persist(productEppelBuffetBierGrosz);
        em.persist(productEppelBuffetBierKlein);
        em.persist(productEppelBuffetCola);
        em.persist(productEppelBuffetWeinGespr);
        em.persist(productEppelBuffetStifterl);
        em.persist(productEppelBuffetEistee);
        em.persist(productEppelBuffetCapri);
        em.persist(productEppelBuffetRedBull);
        em.persist(productEppelBuffetCafemio);
        em.persist(productEppelBuffetMelange);
        em.persist(productEppelBuffetGroszerBrauner);
        em.persist(productEppelBuffetKleinerBrauner);

        em.flush();
    }

    private String urlImgProdBuffet(String name) {
        return "http://www.eppel-boote.at/regkas/images/product/" + name + "_100x100.png";
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
            em.createNativeQuery("DELETE FROM cashbox_journal").executeUpdate();
            em.createNativeQuery("DELETE FROM system_journal").executeUpdate();
            em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
            // reset sequence for ids and clear cache
            em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();
            em.flush();
            em.clear();
        }
    }
}
