package com.harryPotter.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {
	
	

	public static<U,V> PageableResponse<V> getPageableResponse(Page<U> page,Class<V> type){
		
		List<U> entity = page.getContent();
		List<V> dtoList = entity.stream().map(obj -> new ObjectMapper().convertValue(obj, type)).collect(Collectors.toList());
		PageableResponse<V> response = new PageableResponse<>();
		response.setContent(dtoList);
		response.setPageSize(page.getSize());
		response.setPageNumber(page.getNumber());
		response.setTotalPages(page.getTotalPages());
		response.setTotalElement(page.getNumberOfElements());
		response.setLastPage(page.isLast());
	
		return response;
	}
}
