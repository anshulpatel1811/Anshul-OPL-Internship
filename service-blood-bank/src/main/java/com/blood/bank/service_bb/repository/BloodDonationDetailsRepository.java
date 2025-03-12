package com.blood.bank.service_bb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blood.bank.service_bb.domain.BloodDonationDetails;

public interface BloodDonationDetailsRepository extends JpaRepository<BloodDonationDetails, Long>{

	Optional<List<BloodDonationDetails>> findByDonorUserName(String donorUsername);
	
	Optional<BloodDonationDetails> findByBloodGroupName(String bgname);

}
