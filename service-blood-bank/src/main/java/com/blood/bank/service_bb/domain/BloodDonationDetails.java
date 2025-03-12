package com.blood.bank.service_bb.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.blood.bank.service_bb.enums.BloodType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "db_blood_donation_details")
public class BloodDonationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String donorUserName;
	private String bloodGroupId;
	private String bloodGroupName;
	private Integer donatedBloodUnit;
	
	@Enumerated(EnumType.STRING)
	private BloodType bloodType;
	
	@CreationTimestamp
	private Date dateOfDonation;
	private String placeOfDonation;
	
}
