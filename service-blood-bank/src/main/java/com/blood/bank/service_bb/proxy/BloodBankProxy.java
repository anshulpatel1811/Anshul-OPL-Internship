package com.blood.bank.service_bb.proxy;

import com.blood.bank.service_bb.enums.BloodGroup;
import com.blood.bank.service_bb.enums.BloodType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodBankProxy {

	private Long id;
	
	private BloodGroup bbGroup;
	
	private String description;
	
	private String type;
}
