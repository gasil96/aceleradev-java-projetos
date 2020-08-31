package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@EntityListeners(Submission.class)
@Table(name = "SUBMISSION")
public class Submission {

    @EmbeddedId
    private SubmissionId id;

    @Column(name = "score")
    @NotNull
    private Double score;

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

    public SubmissionId getId() {
        return id;
    }

    public void setId(SubmissionId id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
