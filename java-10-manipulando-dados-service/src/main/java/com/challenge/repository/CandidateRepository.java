package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, CandidateId> {

    @Query("select c from Candidate c where c.id.acceleration.id = :id")
    List<Candidate> findByAccelerationId(@Param("id") Long accelerationId);

    @Query(" select c from Candidate c where c.id.user.id = :userId and c.id.company = :companyId and " +
            "c.id.acceleration.id = :accelerationId")
    Optional<Candidate> find(@Param("userId") Long userId, @Param("companyId") Long companyId,
                             @Param("accelerationId") Long accelerationId);

    @Query("select c from Candidate c where c.id.company.id = :companyId")
    List<Candidate> findByCompanyId(@Param("companyId") Long companyId);

}
