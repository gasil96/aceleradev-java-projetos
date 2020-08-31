package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select c.id.company from Candidate c where c.id.user.id = :userId")
    List<Company> findByUserId(@Param("userId")Long userId);

    @Query("select distinct c.id.company from Candidate c where c.id.acceleration.id = :id")
    List<Company> findByAccelerationId(@Param("id") Long accelerationId);
}
