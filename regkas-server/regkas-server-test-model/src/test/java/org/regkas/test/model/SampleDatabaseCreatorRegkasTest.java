package org.regkas.test.model;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.model.ReceiptEntity;
import org.regkas.model.ReceiptTypeEntity;

@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
@RunWith(Arquillian.class)
public class SampleDatabaseCreatorRegkasTest {

    @Inject
    private SampleDatabaseCreatorRegkas sampleDatabaseCreator;

    @PersistenceContext(unitName = "regkas")
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testFillDatabase() throws Exception {
        assertEquals(new BigInteger("0"), entityManager.createNativeQuery("SELECT COUNT(*) FROM company").getSingleResult());
        sampleDatabaseCreator.fillDatabase(entityManager);
        assertEquals(new BigInteger("3"), entityManager.createNativeQuery("SELECT COUNT(*) FROM company").getSingleResult());
        System.out.println(
            entityManager
                .createQuery("FROM ReceiptTypeEntity r WHERE name='Standard-Beleg'", ReceiptTypeEntity.class)
                .getSingleResult()
                .getClass()
                .getName());
        System.out.println(
            entityManager
                .createQuery("FROM ReceiptEntity r WHERE r.receiptId='2015-0000001'", ReceiptEntity.class)
                .getResultList().get(0)
                .getReceiptType()
                .getClass()
                .getName());
        sampleDatabaseCreator.clearDatabase(entityManager);
        assertEquals(new BigInteger("0"), entityManager.createNativeQuery("SELECT COUNT(*) FROM company").getSingleResult());
    }

    @Test
    @Transactional
    @Ignore
    public void createSqlStatementsToFillTableCashboxJournalFromReceipts() throws Exception {
        List resultList = entityManager
            .createQuery(
                "SELECT r.receiptId, r.receiptDate, r.receiptType.name, r.cashBox.id FROM ReceiptEntity r where r.cashBox.id in (14, 15) ORDER BY r.receiptDate")
            .getResultList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Object element : resultList) {
            Object[] x = (Object[]) element;
            String receiptId = (String) x[0];
            String receiptDate = ((LocalDateTime) x[1]).format(formatter);
            String receiptType = (String) x[2];
            Long cashBoxId = (Long) x[3];
            System.out.println(
                "|insert into cashbox_journal (id, version, cashbox_id, messagedate, message) values (nextval('hibernate_sequence'), 0, " +
                    cashBoxId +
                    ", '" +
                    receiptDate +
                    "', 'create receipt " +
                    receiptId +
                    ", " +
                    receiptType +
                    "');");
        }
    }
}
