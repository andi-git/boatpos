package org.regkas.test.model;

import com.google.common.collect.Sets;
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
    public void fillDatabase(EntityManager em) {
        // clear cache and reset sequence for ids
        em.flush();
        em.clear();
        em.createNativeQuery("ALTER SEQUENCE hibernate_sequence RESTART WITH 1").executeUpdate();

        AddressEntity address1 = new AddressEntity(null, null, "Street 1", "1220", "Vienna", "Austria", new HashSet<>());
        AddressEntity address2 = new AddressEntity(null, null, "Street 2", "1220", "Vienna", "Austria", new HashSet<>());
        UserEntity user1 = new UserEntity(null, null, true, 1, "", "", "Maria Musterfrau", "abc123", null);
        UserEntity user2 = new UserEntity(null, null, true, 2, "", "", "Max Mustermann", "xyz789", null);
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
        ProductEntity product1 = new ProductEntity(null, null, true, 1, 'A', "", "", "Cola", productGroup6, new HashSet<>(), new BigDecimal("2.50"));
        ProductEntity product2 = new ProductEntity(null, null, true, 1, 'B', "", "", "Cornetto", productGroup2, new HashSet<>(), new BigDecimal("2.00"));
        ProductEntity product3 = new ProductEntity(null, null, true, 1, 'C', "", "", "Wurstsemmel", productGroup1, new HashSet<>(), new BigDecimal("2.20"));

        ReceiptTypeEntity receiptType1 = new ReceiptTypeEntity(null, null, true, 1, "", "", "Start-Beleg");
        ReceiptTypeEntity receiptType2 = new ReceiptTypeEntity(null, null, true, 2, "", "", "Standard-Beleg");
        ReceiptTypeEntity receiptType3 = new ReceiptTypeEntity(null, null, true, 3, "", "", "Storno-Beleg");
        ReceiptTypeEntity receiptType4 = new ReceiptTypeEntity(null, null, true, 4, "", "", "Training-Beleg");
        ReceiptTypeEntity receiptType5 = new ReceiptTypeEntity(null, null, true, 5, "", "", "Null-Beleg");
        ReceiptEntity receipt1 = new ReceiptEntity(null, null, "0000001", LocalDateTime.of(2015, 7, 1, 12, 00, 13), "12345", "sign", company, cashBox1, user1, receiptType2, new HashSet<>());
        ReceiptEntity receipt2 = new ReceiptEntity(null, null, "0000001", LocalDateTime.of(2015, 7, 1, 12, 00, 13), "12345", "sign", company, cashBox1, user1, receiptType2, new HashSet<>());
        ReceiptElementEntity receiptElement11 = new ReceiptElementEntity(null, null, product1, receipt1, new BigDecimal("5.00"), 2);
        ReceiptElementEntity receiptElement12 = new ReceiptElementEntity(null, null, product2, receipt1, new BigDecimal("6.00"), 3);
        ReceiptElementEntity receiptElement21 = new ReceiptElementEntity(null, null, product3, receipt2, new BigDecimal("11.00"), 5);
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
