package org.regkas.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.boatpos.common.model.AbstractEntity;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "systemJournal")
public class SystemJournalEntity extends AbstractEntity {

    @NotNull
    @Size(min = 3)
    @Expose
    private String message;

    @NotNull
    @Expose
    private LocalDateTime messageDate;

    public SystemJournalEntity() {}

    public SystemJournalEntity(Long id, Integer version, String message, LocalDateTime messageDate) {
        super(id, version);
        this.message = message;
        this.messageDate = messageDate;
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
}
