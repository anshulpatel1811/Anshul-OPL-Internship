package com.blood.bank.service_bb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blood.bank.service_bb.domain.BloodBank;
import com.blood.bank.service_bb.domain.BloodDonationDetails;
import com.blood.bank.service_bb.domain.BloodGroupAvailbilityStatus;
import com.blood.bank.service_bb.domain.PatientBloodUtilizationHistory;
import com.blood.bank.service_bb.enums.BloodGroup;
import com.blood.bank.service_bb.proxy.BloodBankProxy;
import com.blood.bank.service_bb.proxy.BloodDonationDetailsProxy;
import com.blood.bank.service_bb.proxy.BloodGroupAvailbilityStatusProxy;
import com.blood.bank.service_bb.proxy.PatientBloodUtilizationHistoryProxy;
import com.blood.bank.service_bb.repository.BloodBankRepository;
import com.blood.bank.service_bb.repository.BloodDonationDetailsRepository;
import com.blood.bank.service_bb.repository.BloodGroupAvailbilityStatusRepository;
import com.blood.bank.service_bb.repository.PatientBloodUtilizationHistoryRepository;
import com.blood.bank.service_bb.service.BBService;
import com.blood.bank.service_bb.utils.MapperUtile;

import jakarta.transaction.Transactional;

@Service
public class BBServiceImpl implements BBService{

	@Autowired
	private BloodBankRepository bloodBankRepository;
	
	@Autowired
	private BloodDonationDetailsRepository bloodDonationDetailsRepository;
	
	@Autowired
	private BloodGroupAvailbilityStatusRepository availbilityStatusRepository;
	
	@Autowired
	private PatientBloodUtilizationHistoryRepository bloodUtilizationHistoryRepository;
	
	@Override
	public String saveBloodGroupDetais(BloodBankProxy bloodBankProxy) {
		BloodBank bank = MapperUtile.convertValue(bloodBankProxy, BloodBank.class);
		bloodBankRepository.save(bank);
		return "Data has been saved !!";
	}

	@Override
	public List<BloodBankProxy> getAllBloodBankMasterData() {
		List<BloodBank> list = bloodBankRepository.findAll();
		return MapperUtile.convertListOfValue(list, BloodBankProxy.class);
	}

	@Override
	public String saveDonatedBloodGroupDetails(BloodGroupAvailbilityStatusProxy availbilityStatusProxy) {
//		BloodGroupAvailbilityStatus status = MapperUtile.convertValue(availbilityStatusProxy, BloodGroupAvailbilityStatus.class);
//		Optional<BloodGroupAvailbilityStatus> bloodGroupName = availbilityStatusRepository.findByBloodGroupName(status.getBloodGroupName());
//		List<BloodDonationDetails> donationDetails2 = status.getDonationDetails();
//		if(!bloodGroupName.isEmpty()) {
//			BloodGroupAvailbilityStatus availbilityStatus = bloodGroupName.get();
//			List<BloodDonationDetails> donationDetails = availbilityStatus.getDonationDetails();
//			donationDetails.add(donationDetails2.get(0));
//			status.setDonationDetails(donationDetails);
//			status.setId(availbilityStatus.getId());
//			status.setAvailabBloodInUnit(availbilityStatus.getAvailabBloodInUnit()+status.getDonationDetails().get(0).getDonatedBloodUnit());
//			
//		}else {
//			status.setAvailabBloodInUnit(status.getDonationDetails().get(0).getDonatedBloodUnit());
//		}
//		
//		availbilityStatusRepository.save(status);
//		return "Data has been saved !!";
		
		// Convert proxy to entity
	    BloodGroupAvailbilityStatus status = MapperUtile.convertValue(availbilityStatusProxy, BloodGroupAvailbilityStatus.class);
	    
	    // Fetch the existing BloodGroupAvailbilityStatus by name
	    Optional<BloodGroupAvailbilityStatus> existingStatusOpt = availbilityStatusRepository.findByBloodGroupName(status.getBloodGroupName());
	    
	    // Handle the case where the blood group exists
	    if (existingStatusOpt.isPresent()) {
	        BloodGroupAvailbilityStatus existingStatus = existingStatusOpt.get();
	        
	        // Get existing donation details and add the new one
	        List<BloodDonationDetails> existingDonationDetails = existingStatus.getDonationDetails();
	        existingDonationDetails.add(status.getDonationDetails().get(0)); // Add the first donation from the new status
	        
	        // Update the available blood units
	        int updatedBloodUnits = existingStatus.getAvailabBloodInUnit() + status.getDonationDetails().get(0).getDonatedBloodUnit();
	        
	        // Set updated values to the existing status
	        existingStatus.setDonationDetails(existingDonationDetails);
	        existingStatus.setAvailabBloodInUnit(updatedBloodUnits);
	        
	        // Save the updated BloodGroupAvailbilityStatus
	        availbilityStatusRepository.save(existingStatus);
	        
	    } else {
	        // If the blood group does not exist, initialize a new one
	        int bloodUnits = status.getDonationDetails().get(0).getDonatedBloodUnit();
	        status.setAvailabBloodInUnit(bloodUnits);
	        
	        // Save the new status with donation details
	        availbilityStatusRepository.save(status);
	    }
	    
	    return "Data has been saved successfully!";
	}

	@Override
	public List<BloodDonationDetailsProxy> getAllDonationDetails(String donorUsername) {
		List<BloodDonationDetails> list = bloodDonationDetailsRepository.findByDonorUserName(donorUsername).orElseThrow(()-> new RuntimeException("Details Not found with given input !!"));
		return MapperUtile.convertListOfValue(list, BloodDonationDetailsProxy.class);
	}

	@Override
	public String deleteBloodGroupFromMasterData(Long bloodGroupId) {
		bloodBankRepository.deleteById(bloodGroupId);
		return "Blood Group has been deleted !!";
	}

	@Override
	public BloodGroupAvailbilityStatusProxy getBloodGroupDetails(String bloodGroupName) {
		BloodGroupAvailbilityStatus availbilityStatus = availbilityStatusRepository.findByBloodGroupName(bloodGroupName).orElseThrow(()->  new RuntimeException("BloodGroup Not found with given input !!"));
		availbilityStatus.setDonationDetails(null);
		return MapperUtile.convertValue(availbilityStatus, BloodGroupAvailbilityStatusProxy.class);
	}

	@Override
	public String saveOrderDetails(PatientBloodUtilizationHistoryProxy bloodUtilizationHistoryProxy) {
		PatientBloodUtilizationHistory value = MapperUtile.convertValue(bloodUtilizationHistoryProxy, PatientBloodUtilizationHistory.class);
		bloodUtilizationHistoryRepository.save(value);
		return "Order has been placed Successfully !!";
	}

	@Transactional
	@Override
	public String deleteBloodGroupByBGName(String name) {
		BloodGroup bloodGroup = BloodGroup.valueOf(name);
		bloodBankRepository.deleteByBbGroup(bloodGroup);
		return "Delete BloodGroup !!";
	}

}
