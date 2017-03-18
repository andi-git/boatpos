package org.regkas.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "cashboxJournal")
public class CashboxJournalEntity {

    @NotNull
    @Size(min = 3)
    @Expose
    private String message;

    @NotNull
    @Expose
    @Id
    private LocalDateTime messageDate;

    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private CashBoxEntity cashBox;

    public CashboxJournalEntity() {}

    public CashboxJournalEntity(String message, LocalDateTime messageDate, CashBoxEntity cashBox) {
        this.message = message;
        this.messageDate = messageDate;
        this.cashBox = cashBox;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(LocalDateTime messageDate) {
        this.messageDate = messageDate;
    }

    public CashBoxEntity getCashBox() {
        return cashBox;
    }

    public void setCashBox(CashBoxEntity cashBox) {
        this.cashBox = cashBox;
    }
}
