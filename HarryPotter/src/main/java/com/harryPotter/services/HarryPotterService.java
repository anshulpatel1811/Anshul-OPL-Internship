package com.harryPotter.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.harryPotter.domain.HarryPotter;
import com.harryPotter.proxy.HarryPotterProxy;
import com.harryPotter.util.PageableResponse;

public interface HarryPotterService {

		// create 
		HarryPotterProxy create(HarryPotterProxy harPotterProxy);
		
		// create bulk Data 
		String createBulkData(int num);
		
		// get All
		List<HarryPotterProxy> getAll();
		
		// get single
		HarryPotterProxy getById(Long id);
		
		//get all Data
		Page<HarryPotterProxy> getAllHarrryPotter(int pageNumber,int pageSize,String sortBy,String sortDir);

		//get all Data
		PageableResponse<HarryPotterProxy> getAllHarrryPotter2(int pageNumber,int pageSize,String sortBy,String sortDir);

		List<HarryPotterProxy> findByHouse(String house);
		
		List<HarryPotterProxy> findByLocationAndHouse(String location,String house);

		List<HarryPotterProxy> findBySpellContaining(String keywords);
		
		List<HarryPotterProxy> findByLocationOrHouse(String location,String house);
		
		String deleteByCharacte_name(String name);
		
		void updateData(Long id,String characterName);
		
		//HarryPotterProxy getData(Long id);
}
