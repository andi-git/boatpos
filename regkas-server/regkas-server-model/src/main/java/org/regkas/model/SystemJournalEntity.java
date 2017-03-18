package org.regkas.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "systemJournal")
public class SystemJournalEntity {

    @NotNull
    @Size(min = 3)
    @Expose
    private String message;

    @NotNull
    @Expose
    @Id
    private LocalDateTime messageDate;

    public SystemJournalEntity() {}

    public SystemJournalEntity(String message, LocalDateTime messageDate) {
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
