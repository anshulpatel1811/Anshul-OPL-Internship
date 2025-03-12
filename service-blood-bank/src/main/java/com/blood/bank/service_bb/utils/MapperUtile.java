package com.blood.bank.service_bb.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtile {

	public static <T> T convertValue(Object fromValue,Class<T> toValueType) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(fromValue, toValueType);
	}
	
	public static <T> List<T> convertListOfValue(List<?> fromValue,Class<T> toValueType) {
		List<T> listValue = new ArrayList<>();
		for(Object o : fromValue) {
			ObjectMapper mapper = new ObjectMapper();
			T convertValue = mapper.convertValue(o, toValueType);
			listValue.add(convertValue);
		}
		return listValue;
	}
}
