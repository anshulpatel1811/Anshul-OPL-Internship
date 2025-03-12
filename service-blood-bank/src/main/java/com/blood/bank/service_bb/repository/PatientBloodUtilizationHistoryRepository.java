package com.blood.bank.service_bb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blood.bank.service_bb.domain.PatientBloodUtilizationHistory;

public interface PatientBloodUtilizationHistoryRepository extends JpaRepository<PatientBloodUtilizationHistory, Long>{

}
