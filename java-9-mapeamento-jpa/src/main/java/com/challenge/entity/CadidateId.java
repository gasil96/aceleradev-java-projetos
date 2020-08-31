package com.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CadidateId implements Serializable {

    @ManyToOne
    @JoinColumn(name  = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "acceleration_id")
    private Acceleration acceleration;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;}

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof CadidateId)) return false;
//        CadidateId that = (CadidateId) o;
//        return user.equals(that.user) &&
//                acceleration.equals(that.acceleration) &&
//                company.equals(that.company);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(user, acceleration, company);
//    }
//}

