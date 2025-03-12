package com.blood.bank.service_bb.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.blood.bank.service_bb.enums.BloodGroup;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PatientBloodUtilizationHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private BloodGroup bloodGroup;
	private Integer utilizedUnit;
	
	@CreationTimestamp
	private Date date;
	private String hospitalName;
}
