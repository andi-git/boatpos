package org.regkas.repository.core.model;

import static com.google.common.base.Preconditions.checkNotNull;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.common.repository.core.model.ModelCore;
import org.regkas.model.CashboxJournalEntity;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.CashboxJournal;
import org.regkas.repository.api.values.JournalDate;
import org.regkas.repository.api.values.JournalMessage;

public class CashboxJournalCore extends ModelCore<CashboxJournal, CashboxJournalEntity> implements CashboxJournal {

    public CashboxJournalCore(JournalDate journalDate, JournalMessage journalMessage, CashBox cashBox) {
        checkNotNull(journalDate, "'journalDate' must not be null");
        checkNotNull(journalMessage, "'journalMessage' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        setJournalDate(journalDate);
        setJournalMessage(journalMessage);
        setCashBox(cashBox);
    }

    public CashboxJournalCore(CashboxJournalEntity cashboxJournalEntity) {
        super(cashboxJournalEntity);
    }

    @Override
    public JournalDate getJournalDate() {
        return new JournalDate(getEntity().getMessageDate());
    }

    private void setJournalDate(JournalDate journalDate) {
        getEntity().setMessageDate(SimpleValueObject.nullSafe(journalDate));
    }

    @Override
    public JournalMessage getJournalMessage() {
        return new JournalMessage(getEntity().getMessage());
    }

    private void setJournalMessage(JournalMessage journalMessage) {
        getEntity().setMessage(SimpleValueObject.nullSafe(journalMessage));
    }

    @Override
    public CashBox getCashBox() {
        return new CashBoxCore(getEntity().getCashBox());
    }

    private CashboxJournal setCashBox(CashBox cashBox) {
        if (cashBox != null) getEntity().setCashBox(cashBox.asEntity());
        return this;
    }
}
