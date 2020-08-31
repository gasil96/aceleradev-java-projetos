package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccelerationRepository extends JpaRepository<Acceleration, Long> {

    @Query("select c.id.acceleration from Candidate c where c.id.company.id = :companyId")
    List<Acceleration> findByCompanyId(@Param("companyId")Long companyId);

}
