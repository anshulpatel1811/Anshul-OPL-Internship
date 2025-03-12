package com.harryPotter.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.harryPotter.domain.HarryPotter;
import com.harryPotter.proxy.ApiResponce;
import com.harryPotter.proxy.HarryPotterProxy;
import com.harryPotter.repositories.HarryPotterRepo;
import com.harryPotter.services.HarryPotterService;
import com.harryPotter.util.Helper;
import com.harryPotter.util.Mapper;
import com.harryPotter.util.PageableResponse;

import jakarta.transaction.Transactional;

@Service
public class HarryPotterServiceImpl implements HarryPotterService {

	@Autowired
	private HarryPotterRepo repo;

	@Autowired
	private Mapper mapper;

	@Override
	public HarryPotterProxy create(HarryPotterProxy harPotterProxy) {
		//HarryPotter harryPotter = mapper.proxyToDomainForHarryPotter(harPotterProxy);
	//	return mapper.domainToProxyForHarryPotter(repo.saveData());
		try {
			repo.saveData();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public String createBulkData(int num) {
		for (int i = 1; i <= num; i++) {
			HarryPotterProxy proxy = generateAndSaveBulkData();
			repo.save(mapper.proxyToDomainForHarryPotter(proxy));
		}
		return "Bulk Data is saved !!";
	}

	@Override
	public List<HarryPotterProxy> getAll() {
		List<HarryPotter> list = repo.findAll();
		return mapper.listDominToProxyForHarryPotter(list);
	}

	@Override
	public HarryPotterProxy getById(Long id) {
		HarryPotter harryPotter = repo.findById(id).get();
		HarryPotterProxy proxy = mapper.domainToProxyForHarryPotter(repo.save(harryPotter));
		return proxy;
	}

	private HarryPotterProxy generateAndSaveBulkData() {
		Faker f = new Faker();
		HarryPotterProxy proxy = new HarryPotterProxy();

		proxy.setCharacterName(f.harryPotter().character());
		proxy.setHouse(f.harryPotter().house());
		proxy.setLocation(f.harryPotter().location());
		proxy.setQuote(f.harryPotter().quote());
		proxy.setSpell(f.harryPotter().spell());

		return proxy;
	}

	@Override
	public Page<HarryPotterProxy> getAllHarrryPotter(int pageNumber, int pageSize, String sortBy, String sortDir) {


		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<HarryPotter> hpPage = repo.findAll(pageable);

		List<HarryPotterProxy> collect = hpPage.getContent().stream().map(hp -> mapper.domainToProxyForHarryPotter(hp))
				.collect(Collectors.toList());

		Page<HarryPotterProxy> pages = new PageImpl<HarryPotterProxy>(collect, pageable, hpPage.getTotalElements());

		return pages;
	}


	@Override
	public PageableResponse<HarryPotterProxy> getAllHarrryPotter2(int pageNumber, int pageSize, String sortBy,
			String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

		// page number default start 0
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<HarryPotter> page = repo.findAll(pageable);

		PageableResponse<HarryPotterProxy> response = Helper.getPageableResponse(page, HarryPotterProxy.class);

		return response;
	}

	@Override
	public List<HarryPotterProxy> findByHouse(String house) {
		
		List<HarryPotter> list = repo.findhouse(house);
		return mapper.listDominToProxyForHarryPotter(list);
	}

	@Override
	public List<HarryPotterProxy> findByLocationAndHouse(String location, String house) {
		List<HarryPotter> list = repo.findLocationAndhouse(location, house);
		return mapper.listDominToProxyForHarryPotter(list);
	}

	@Override
	public List<HarryPotterProxy> findBySpellContaining(String keywords) {
		List<HarryPotter> list = repo.findBySpellContaining(keywords);
		return mapper.listDominToProxyForHarryPotter(list);
	}

	@Override
	public List<HarryPotterProxy> findByLocationOrHouse(String location, String house) {
		List<HarryPotter> list = repo.findlocationOrhouse(location, house);
		return mapper.listDominToProxyForHarryPotter(list);
	}

	@Override
	@Transactional
	public String deleteByCharacte_name(String name) {
		repo.deleteDatacharactername(name);
		return "Character is deleted Successfully";
	}

	@Override
	public void updateData(Long id, String characterName) {
		repo.updateData(id, characterName);
		
	}

//	@Override
//	public HarryPotterProxy getData(Long id) {
//		HarryPotter data = repo.getData(id);
//		return mapper.domainToProxyForHarryPotter(data);
//	}

}
