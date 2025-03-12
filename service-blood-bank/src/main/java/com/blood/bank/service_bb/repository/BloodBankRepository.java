package com.blood.bank.service_bb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blood.bank.service_bb.domain.BloodBank;
import com.blood.bank.service_bb.enums.BloodGroup;

public interface BloodBankRepository extends JpaRepository<BloodBank, Long>{

	void deleteByBbGroup(BloodGroup name);

}
