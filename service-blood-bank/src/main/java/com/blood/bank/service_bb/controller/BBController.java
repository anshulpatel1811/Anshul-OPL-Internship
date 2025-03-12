package com.blood.bank.service_bb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blood.bank.service_bb.enums.BloodGroup;
import com.blood.bank.service_bb.proxy.BloodBankProxy;
import com.blood.bank.service_bb.proxy.BloodDonationDetailsProxy;
import com.blood.bank.service_bb.proxy.BloodGroupAvailbilityStatusProxy;
import com.blood.bank.service_bb.proxy.PatientBloodUtilizationHistoryProxy;
import com.blood.bank.service_bb.service.BBService;

@RestController
@RequestMapping("/blood-bank")
public class BBController {

	@Autowired
	private BBService bbService;

	@PostMapping("/saveBloodGroupDetais")
	public ResponseEntity<String> saveBloodGroupDetais(@RequestBody BloodBankProxy bankProxy){
		return new ResponseEntity<String>(bbService.saveBloodGroupDetais(bankProxy), HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllBloodBankMasterData")
	public ResponseEntity<List<BloodBankProxy>> getAllBloodBankMasterData(){
		return new ResponseEntity<>(bbService.getAllBloodBankMasterData(), HttpStatus.OK);
	}
	
	@PostMapping("/saveDonatedBloodGroupDetails")
	public ResponseEntity<String> saveDonatedBloodGroupDetails(@RequestBody BloodGroupAvailbilityStatusProxy availbilityStatusProxy){
		return new ResponseEntity<String>(bbService.saveDonatedBloodGroupDetails(availbilityStatusProxy), HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllDonationDetails/{donorUserName}")
	public ResponseEntity<List<BloodDonationDetailsProxy>> getAllDonationDetails(@PathVariable String donorUserName){
		return new ResponseEntity<>(bbService.getAllDonationDetails(donorUserName), HttpStatus.OK);
	}
	
	@GetMapping("/deleteBGFromMaster/{bgID}")
	public ResponseEntity<String> deleteBloodGroupFromMasterData(@PathVariable Long bgID){
		return new ResponseEntity<>(bbService.deleteBloodGroupFromMasterData(bgID), HttpStatus.OK);
	}
	
	@GetMapping("/getBGDetails/{bgName}")
	public ResponseEntity<BloodGroupAvailbilityStatusProxy> getBloodGroupDetails(@PathVariable String bgName){
		return new ResponseEntity<>(bbService.getBloodGroupDetails(bgName), HttpStatus.OK);
	}
	
	@PostMapping("/saveOrderDetails")
	public ResponseEntity<String> saveOrderDetails(@RequestBody PatientBloodUtilizationHistoryProxy bloodUtilizationHistoryProxy){
		return new ResponseEntity<String>(bbService.saveOrderDetails(bloodUtilizationHistoryProxy), HttpStatus.CREATED);
	}
	
	@GetMapping("/deleteBGFromMasterByName/{BGName}")
	public ResponseEntity<String> deleteBloodGroupByBGName(@PathVariable String BGName){
		return new ResponseEntity<>(bbService.deleteBloodGroupByBGName(BGName), HttpStatus.OK);
	}
}
