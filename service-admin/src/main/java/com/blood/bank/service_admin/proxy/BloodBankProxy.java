package com.blood.bank.service_admin.proxy;

import com.blood.bank.service_admin.enums.BloodGroup;

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
