package com.harryPotter.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harryPotter.domain.HarryPotter;
import com.harryPotter.proxy.HarryPotterProxy;

@Component
public class Mapper {

	@Autowired
	private ObjectMapper mapper;
	
	public HarryPotter proxyToDomainForHarryPotter(HarryPotterProxy proxy) {
		
		return mapper.convertValue(proxy, HarryPotter.class);
	}
	
	public HarryPotterProxy domainToProxyForHarryPotter(HarryPotter proxy) {
		
		return mapper.convertValue(proxy, HarryPotterProxy.class);
	}
	
	public List<HarryPotterProxy> listDominToProxyForHarryPotter(List<HarryPotter> proxy) {
		
		return proxy.stream().map(obj->mapper.convertValue(obj, HarryPotterProxy.class)).collect(Collectors.toList());
	}

	public List<HarryPotter> listProxyToDomainForHarryPotter(List<HarryPotterProxy> proxy) {
	
	return proxy.stream().map(obj->mapper.convertValue(obj, HarryPotter.class)).collect(Collectors.toList());
	}
	
	public Page<HarryPotterProxy> listDominToProxyForHarryPotter(Page<HarryPotter> proxy) {
		
		return (Page<HarryPotterProxy>) proxy.stream().map(obj->mapper.convertValue(obj, HarryPotterProxy.class)).collect(Collectors.toList());
	}
}
