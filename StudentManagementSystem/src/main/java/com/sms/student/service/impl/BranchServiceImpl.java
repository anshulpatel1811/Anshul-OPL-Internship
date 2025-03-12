package com.sms.student.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sms.student.domain.Branch;
import com.sms.student.domain.Student;
import com.sms.student.exception.ListIsEmpty;
import com.sms.student.exception.ResourceNotFoundException;
import com.sms.student.proxy.BranchProxy;
import com.sms.student.proxy.StudentProxy;
import com.sms.student.repo.BranchRepo;
import com.sms.student.repo.StudentRepo;
import com.sms.student.service.BranchServices;
import com.sms.student.util.ApiResponseMessage;
import com.sms.student.util.ConvertData;

@Service
public class BranchServiceImpl implements BranchServices{

	@Autowired
	private BranchRepo repo;
	
	@Autowired
	private StudentRepo srepo;
	
	@Autowired
	private ConvertData c;
	
	@Override
	public BranchProxy create(BranchProxy proxy) {
	//	StudentProxy studentProxy = new StudentProxy();
//		studentProxy.setSid(proxy.getStudent().getSid());
//		studentProxy.setSid(proxy.getStudent().getAddress());
//		studentProxy.setSid(proxy.getStudent().getDob());
//		studentProxy.setSid(proxy.getStudent().getName());
		//studentProxy.setSid(proxy.getStudent().getGender());
//		Student std = c.proxyToDomainForStd(studentProxy);
		
		Branch branch = c.proxyToDomainForBranch(proxy);
		branch.getStudent().setBranch(branch);
		
		Student student = branch.getStudent();
		student.getNum().stream().forEach(obj -> obj.setStudent(student));
		
		
//		studentProxy.setSid(proxy.getStudent().getSid());
//		studentProxy.setGender(proxy.getStudent().getGender());
//		studentProxy.setAddress(proxy.getStudent().getAddress());
//		studentProxy.setDob(proxy.getStudent().getDob());
//		studentProxy.setName(proxy.getStudent().getName());
//		studentProxy.setBranch(proxy);

		
		
		//Student std = c.proxyToDomainForStd(branch);
		
		
		repo.save(branch);
		return proxy;
	}

	@Override
	public BranchProxy updateById(BranchProxy proxy, String id) {
//		Branch branch = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Branch Not Found With this Id !!"));
//		//branch.setBid(proxy.getBid());
//		branch.setName(proxy.getName());
//		branch.setDescription(proxy.getDescription());
//		branch.setStudent(c.proxyToDomainForStd(proxy.getStudent()));
////		Branch branch2 = c.proxyToDomainForBranch(proxy);
////		branch2.getStudent().setBranch(branch2);
//		
//		Branch savedBranch = repo.save(branch);
//		
//		return c.domainToProxyForBranch(savedBranch);
//		
		
		Optional<Branch> br = repo.findById(id);
		if (br.isPresent()) {
			Branch branch = br.get();
 
			if (proxy.getBid() != null)
				branch.setBid(id);
 
			if (proxy.getDescription() != null)
				branch.setDescription(id);
 
			if (proxy.getName() != null)
				branch.setName(id);
 
			if (proxy.getStudent() != null) {
				branch.getStudent().setSid(proxy.getStudent().getSid());
				branch.getStudent().setAddress(proxy.getStudent().getAddress());
				branch.getStudent().setDob(proxy.getStudent().getDob());
				branch.getStudent().setGender(proxy.getStudent().getGender());
				branch.getStudent().setName(proxy.getStudent().getName());
				branch.getStudent().setNum(c.listproxyToDomainForMobile(proxy.getStudent().getNum()));
				branch.getStudent().setBranch(branch);
			}
 
			Student student = branch.getStudent();
			student.getNum().stream().forEach(obj -> obj.setStudent(student));
 
			Branch save = repo.save(branch);
 
			return proxy;
		} else {
			throw new ResourceNotFoundException("Either id is missing in database or id is invalid.");
		}
 
	}

	@Override
	public ApiResponseMessage deleteById(String id) {
		Branch branch = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Branch Not Found With this Id !!"));
		repo.delete(branch);
		ApiResponseMessage message = ApiResponseMessage.builder().message("Branch Deleted Successfully !!").success(true).status(HttpStatus.OK).build();
		return message;
	}

	@Override
	public BranchProxy findById(String id) {
		Branch branch = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Branch Not Found With this Id !!"));
		branch.getStudent().setBranch(null);
		branch.getStudent().getNum().stream().forEach(o -> o.setStudent(null));
		System.out.println(branch);
		BranchProxy entityToProxy = c.domainToProxyForBranch(branch);
		return entityToProxy;
		
		//return c.domainToProxyForBranch(branch);
	}

	@Override
	public List<BranchProxy> findAll() {
		
		List<Branch> list = repo.findAll();
		if(list.isEmpty()) {
			throw new ListIsEmpty();
		}
		
//		List<BranchProxy> branchProxies = new ArrayList<>();
//		
//		for (Branch br : list) {
//			
//			br.getStudent().setBranch(null);
//			branchProxies.add(c.domainToProxyForBranch(br));
//		}
//		return branchProxies;
//		branch.getStudent().setNum(null);
//		branch.getStudent().setBranch(null);
		
//		List<BranchProxy> list2 = list.stream().map(obj->c.domainToProxyForBranch(obj)).collect(Collectors.toList());
//		return list2;
		
		List<BranchProxy> branchProxies = new ArrayList<>();
		
		for (Branch br : list) {
			br.getStudent().setBranch(null);
			br.getStudent().getNum().stream().forEach(o -> o.setStudent(null));
			branchProxies.add(c.domainToProxyForBranch(br));
		}
		return branchProxies;
	}

	@Override
	public ApiResponseMessage deleteAll() {
		repo.deleteAll();
		ApiResponseMessage message = ApiResponseMessage.builder().message(" All Branch Deleted Successfully !!").success(true).status(HttpStatus.OK).build();
		return message;
	}

}
