package com.blood.bank.service_admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blood.bank.service_admin.enums.BloodGroup;
import com.blood.bank.service_admin.proxy.AdminProxy;
import com.blood.bank.service_admin.proxy.BloodBankProxy;
import com.blood.bank.service_admin.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	// delete User By Username
	@GetMapping("/deleteUser/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable String username){
		return new ResponseEntity<String>(adminService.deleteUser(username),HttpStatus.OK);
	}
	
	// delete bloodGroup 
	@GetMapping("/deleteBloodGroup/{bloodGroup}")
	public ResponseEntity<String> deleteBloodGroup(@PathVariable String bloodGroup){
		return new ResponseEntity<String>(adminService.deleteBloodGroup(bloodGroup),HttpStatus.OK);
	}
	
	// delete Admin 
	@GetMapping("/deleteAdmin/{username}")
	public ResponseEntity<String> deleteAdmin(@PathVariable String username){
		return new ResponseEntity<String>(adminService.deleteAdminByUsername(username),HttpStatus.OK);
	}
	
	// get Admin 
	@PostMapping("/getAdmin/{username}")
	public ResponseEntity<AdminProxy> getAdmin(@PathVariable String username){
		return new ResponseEntity<AdminProxy>(adminService.getAdminByUsername(username),HttpStatus.OK);
	}
	
	// get All Admin 
	@PostMapping("/getAllAdmin")
	public ResponseEntity<List<AdminProxy>> getAllAdmin(){
		return new ResponseEntity<>(adminService.getAllAdmins(),HttpStatus.OK);
	}
	
	// register
	@PostMapping("/register")
	public ResponseEntity<AdminProxy> registerAdmin(@RequestBody AdminProxy adminProxy){
		return new ResponseEntity<>(adminService.saveAdmin(adminProxy),HttpStatus.CREATED);
	}
	
	@PostMapping("/saveBGMasterData")
	public ResponseEntity<String> saveMasterDataOfBG(@RequestBody BloodBankProxy bankProxy){
		return new ResponseEntity<String>(adminService.saveBloodGroupDetais(bankProxy), HttpStatus.OK);
	}
}
