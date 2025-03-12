package com.blood.bank.service_bb.service;

import java.util.List;

import com.blood.bank.service_bb.proxy.BloodBankProxy;
import com.blood.bank.service_bb.proxy.BloodDonationDetailsProxy;
import com.blood.bank.service_bb.proxy.BloodGroupAvailbilityStatusProxy;
import com.blood.bank.service_bb.proxy.PatientBloodUtilizationHistoryProxy;

public interface BBService {

	//admin
	String saveBloodGroupDetais(BloodBankProxy bloodBankProxy);//Master
	
	//donor
	List<BloodBankProxy> getAllBloodBankMasterData();//Master
	
	//donor
	String saveDonatedBloodGroupDetails(BloodGroupAvailbilityStatusProxy availbilityStatusProxy);
	
	//donor
	List<BloodDonationDetailsProxy> getAllDonationDetails(String donorUsername);
	
	//admin
	String deleteBloodGroupFromMasterData(Long bloodGroupId);
	
	//user
	BloodGroupAvailbilityStatusProxy getBloodGroupDetails(String bloodGroupName);

	String saveOrderDetails(PatientBloodUtilizationHistoryProxy bloodUtilizationHistoryProxy);
	
	String deleteBloodGroupByBGName(String name);
}
