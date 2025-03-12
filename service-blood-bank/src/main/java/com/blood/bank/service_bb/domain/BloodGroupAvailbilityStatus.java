package com.blood.bank.service_bb.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "db_bloodgroup_availbility_status")
public class BloodGroupAvailbilityStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String bloodGroupId;
	private String bloodGroupName;
	private Integer availabBloodInUnit;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bas_fk")
	private List<BloodDonationDetails> donationDetails;
}
