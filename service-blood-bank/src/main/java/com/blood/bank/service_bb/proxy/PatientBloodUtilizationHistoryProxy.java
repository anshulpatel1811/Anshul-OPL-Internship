package com.blood.bank.service_bb.proxy;

import java.util.Date;

import com.blood.bank.service_bb.enums.BloodGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientBloodUtilizationHistoryProxy {

	private Long id;
	
	private String name;
	private BloodGroup bloodGroup;
	private Integer utilizedUnit;
	private Date date;
	private String hospitalName;
}
