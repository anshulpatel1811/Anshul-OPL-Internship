package com.blood.bank.service_admin.service;

import java.util.List;

import com.blood.bank.service_admin.proxy.AdminProxy;
import com.blood.bank.service_admin.proxy.BloodBankProxy;

public interface AdminService {

	String deleteUser(String username);

	String deleteBloodGroup(String bloodGroup);

	List<AdminProxy> getAllAdmins();
	
	AdminProxy getAdminByUsername(String username);

	String deleteAdminByUsername(String username);
	
	AdminProxy saveAdmin(AdminProxy adminProxy);
	
	String saveBloodGroupDetais(BloodBankProxy bloodBankProxy);
}
