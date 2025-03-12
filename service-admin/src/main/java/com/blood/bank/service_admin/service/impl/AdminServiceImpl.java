package com.blood.bank.service_admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blood.bank.service_admin.domain.Admin;
import com.blood.bank.service_admin.proxy.AdminProxy;
import com.blood.bank.service_admin.proxy.BloodBankProxy;
import com.blood.bank.service_admin.repository.AdminRepository;
import com.blood.bank.service_admin.service.AdminService;
import com.blood.bank.service_admin.utils.MapperUtile;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String baseUrlbb = "http://SERVICE-BLOOD-BANK/blood-bank";
	private final String baseUrlUser = "http://SERVICE-USER/Userr";
	
	
	@Override
	public String deleteUser(String username) {
		ResponseEntity<String> exchange = restTemplate.exchange(baseUrlUser+"/deleteUser/{u}", HttpMethod.DELETE,null, String.class, username);
		return exchange.getBody();
	}

	@Override
	public String deleteBloodGroup(String bloodGroup) {
		ResponseEntity<String> exchange = restTemplate.exchange(baseUrlbb+"/deleteBGFromMasterByName"+"/{u}", HttpMethod.GET,null, String.class, bloodGroup);
		return exchange.getBody();
	}

	@Override
	public List<AdminProxy> getAllAdmins() {
		List<Admin> list = adminRepository.findAll();
		return MapperUtile.convertListOfValue(list, AdminProxy.class);
	}

	@Override
	public AdminProxy getAdminByUsername(String username) {
		Admin admin = adminRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User Not Found With given UserName !!"));
		return MapperUtile.convertValue(admin, AdminProxy.class);
	}

	@Override
	public String deleteAdminByUsername(String username) {
		adminRepository.deleteByUsername(username);
		return "Admin has been deleted with username - "+username;
	}

	@Override
	public AdminProxy saveAdmin(AdminProxy adminProxy) {
		Admin admin = MapperUtile.convertValue(adminProxy, Admin.class);
		Admin savedAdmin = adminRepository.save(admin);
		return MapperUtile.convertValue(savedAdmin, AdminProxy.class);
	}

	@Override
	public String saveBloodGroupDetais(BloodBankProxy bloodBankProxy) {
		
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(baseUrlbb+"/saveBloodGroupDetais", bloodBankProxy, String.class);
		
		return postForEntity.getBody();
	}

}
