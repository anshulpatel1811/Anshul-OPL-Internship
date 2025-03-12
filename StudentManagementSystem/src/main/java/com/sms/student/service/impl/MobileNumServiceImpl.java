	package com.sms.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sms.student.domain.MobileNum;
import com.sms.student.domain.Student;
import com.sms.student.exception.ListIsEmpty;
import com.sms.student.exception.ResourceNotFoundException;
import com.sms.student.proxy.MobileNumProxy;
import com.sms.student.repo.MobileNumRepo;
import com.sms.student.service.MobileNumServices;
import com.sms.student.util.ApiResponseMessage;
import com.sms.student.util.ConvertData;

@Service
public class MobileNumServiceImpl implements MobileNumServices{

	@Autowired
	private MobileNumRepo mobileNumRepo;
	
	@Autowired
	private ConvertData c;
	
	@Override
	public MobileNumProxy create(MobileNumProxy proxy) {
		
		MobileNum num = c.proxyToDomainForMob(proxy);
//		Student student = num.getStudent();
//		List<MobileNum> list = student.getNum();
//		num.getStudent().setNum(list);

		MobileNum save = mobileNumRepo.save(num);
		
		return c.domainToProxyForMob(save);
	}

	@Override
	public MobileNumProxy updateById(MobileNumProxy proxy, String id) {
		MobileNum Mob = mobileNumRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Branch Not Found With this Id !!"));

		Mob.setMid(proxy.getMid());
		Mob.setNumber(proxy.getNumber());
		Mob.setCardName(proxy.getCardName());
		Mob.getStudent().setName(proxy.getStudent().getName());
		Mob.getStudent().setAddress(proxy.getStudent().getAddress());
		Mob.getStudent().setDob(proxy.getStudent().getDob());
		Mob.getStudent().setGender(proxy.getStudent().getGender());
		Mob.getStudent().setSid(proxy.getStudent().getSid());
		Mob.getStudent().setBranch(c.proxyToDomainForBranch(proxy.getStudent().getBranch()));
				
		
		MobileNum save = mobileNumRepo.save(Mob);
		return proxy;
	}

	@Override
	public ApiResponseMessage deleteById(String id) {
		MobileNum Mob = mobileNumRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Branch Not Found With this Id !!"));
		mobileNumRepo.delete(Mob);
		ApiResponseMessage message = ApiResponseMessage.builder().message("Branch Deleted Successfully !!").success(true).status(HttpStatus.OK).build();
		return message;
	}

	@Override
	public MobileNumProxy findById(String id) {
		MobileNum Mob = mobileNumRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Branch Not Found With this Id !!"));
		
		Mob.getStudent().setNum(null);
		Mob.getStudent().getBranch().setStudent(null);;
		MobileNumProxy entityToProxy = c.domainToProxyForMob(Mob);
		return entityToProxy;

	}

	@Override
	public List<MobileNumProxy> findAll() {
		List<MobileNum> list = mobileNumRepo.findAll();
		if(list.isEmpty()) {
			throw new ListIsEmpty();
		}
		
		
		List<MobileNumProxy> mob = new ArrayList<>();
		
		for (MobileNum br : list) {
			br.getStudent().getBranch().setStudent(null);
			//br.getStudent().setBranch(null);
			br.getStudent().setNum(null);
			//br.getStudent().getNum().stream().forEach(obj -> obj.setStudent(null));
			//branchProxies.add(BranchEntitytoBranchProxy(br));
			mob.add(c.domainToProxyForMob(br));
			
		}
		return mob;
	}

	@Override
	public ApiResponseMessage deleteAll() {
		mobileNumRepo.deleteAll();
		ApiResponseMessage message = ApiResponseMessage.builder().message(" All Branch Deleted Successfully !!").success(true).status(HttpStatus.OK).build();
		return message;
	}

}
