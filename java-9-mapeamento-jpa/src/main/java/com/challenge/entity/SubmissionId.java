package com.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubmissionId implements Serializable {


    @ManyToOne
    private User user;

    @ManyToOne
    private Challenge challenge;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubmissionId)) return false;
        SubmissionId that = (SubmissionId) o;
        return user.equals(that.user) &&
                challenge.equals(that.challenge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, challenge);
    }
}
