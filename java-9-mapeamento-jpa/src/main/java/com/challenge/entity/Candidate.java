package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@EntityListeners(Candidate.class)
@Table
public class Candidate implements Serializable {

    @EmbeddedId
    private CadidateId id;

    @NotNull
    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CREATED_AT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    @CreatedDate
    private LocalDateTime created;

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public CadidateId getId() {
        return id;
    }

    public void setId(CadidateId id) {
        this.id = id;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
